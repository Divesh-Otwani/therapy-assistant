package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
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
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.localstore.LocalStorage;
import java.text.SimpleDateFormat;

public class ExerciseListRecentAdapter extends BaseAdapter implements ListAdapter{
    private LocalStorage localStorage;
    private HashMap<Date,Vector<Exercise>> queryExercises;
    private Vector<Exercise> ex;

    public ExerciseListRecentAdapter(Context context){
        localStorage = new LocalStorage(context);
        queryExercises = localStorage.queryExercises();
        ex = collectallexercise();
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

    private Vector<Exercise> collectallexercise(){
        Vector<Exercise> exe = new Vector<>();
        Iterator<Map.Entry<Date, Vector<Exercise>>> entries = queryExercises.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Date, Vector<Exercise>> pair = entries.next();
            for (Exercise ex : pair.getValue()){
                ex.setDate(pair.getKey());
                exe.add(ex);
            }
        }
        exe.sort(new CompareExercisePairs());
        return exe;
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
        dateText.setText(e.getDate().toString());

        view.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                if(e.getQuestions().size() == 0){
                    Intent i = new Intent(context, NoQuestion.class);
                    context.startActivity(i);
                }else{
                    Bundle bundle = new Bundle();
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
