package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.content.Context;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


//BRIAN CHANGED THIS HOPE ALL OK
//import java.sql.Date;
import java.util.Date;
//BRIAN CHANGED THIS


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.localstore.LocalStorage;
import java.text.SimpleDateFormat;

public class ExerciseListRecentAdapter extends BaseAdapter implements ListAdapter{
    private Vector<Exercise> ex;
    private ArrayList<DataSetObserver> observers = new ArrayList<DataSetObserver>();



    public ExerciseListRecentAdapter(Vector<Exercise> exercises){
        ex = exercises;
    }


    public static Date getWeekStartDate() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return new java.sql.Date(calendar.getTime().getTime());
    }

    public static Date getWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, -1);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    boolean isWithinRange(Date testDate) {
        return !(testDate.before(getWeekStartDate()) || testDate.after(getWeekEndDate()));
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        observers.add(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        observers.remove(dataSetObserver);
    }

    public void tellObservers(){
        for (DataSetObserver o : observers){
            synchronized (o){
                o.onChanged();
            }
        }
    }


    @Override
    public int getCount() {
        return ex.size();
    }

    @Override
    public Object getItem(int position) {
        return ex.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Exercise e = ex.get(position);
        final Context context = parent.getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_exercise_list, parent, false);
        }

        TextView dateText = (TextView) view.findViewById(R.id.exercise_date);
        TextView nameText = (TextView) view.findViewById(R.id.exercise_name);
        nameText.setText(e.getName());
        Date date = e.getDate();
        String dateString = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH).format(date);
        dateText.setText(dateString);

        view.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {
                if(e.getQuestions().size() == 0){
                    Intent i = new Intent(context, NoQuestion.class);
                    context.startActivity(i);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("exercise_name", e.getName());
                    bundle.putSerializable("recent_list_arg", e.getQuestions());
                    Intent i = new Intent(context, QuestionAnswer.class);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            }
        });
        return view;
    }
}
