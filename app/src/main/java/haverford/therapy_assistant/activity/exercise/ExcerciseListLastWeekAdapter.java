package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.content.Context;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.localstore.LocalStorage;

public class ExcerciseListLastWeekAdapter extends BaseAdapter implements ListAdapter {
    private LocalStorage localStorage;
    private HashMap<Date,Vector<Exercise>> queryExercises;
    private Vector<Exercise> ex;
    public ExcerciseListLastWeekAdapter(Context context){
        localStorage = new LocalStorage(context);
        queryExercises = localStorage.queryExercises();
        ex = collectallexercise();
    }
    public static Date getWeekStartDate() {
        //Date date = new Date();
        Calendar c = Calendar.getInstance();
        //c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        return new java.sql.Date(c.getTime().getTime());
    }

    public static Date getWeekEndDate() {
        //Date date = new Date();
        Calendar c = Calendar.getInstance();
        //c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        //Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        //Date end = c.getTime();
        return new java.sql.Date(c.getTime().getTime());
    }

    boolean isWithinRange(Date testDate) {
        return !(testDate.before(getWeekStartDate()) || testDate.after(getWeekEndDate()));
    }

    private Vector<Exercise> collectallexercise(){
        Vector<Exercise> exe = new Vector<>();
        Iterator it = queryExercises.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Date d = (Date)pair.getKey();
            if(isWithinRange((Date)pair.getKey())){
                exe.addAll(queryExercises.get(d));
            }

        }
        return exe;


    }
    @Override
    public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_exercises, parent, false);
        }
        TextView name = (TextView) view.findViewById(R.id.exercise);
        name.setText(e.getName());
        view.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, QuestionAnswer.class);
                i.putExtra("questions", e.getQuestions());
                context.startActivity(i);

            }
        });
        return view;
    }
}
