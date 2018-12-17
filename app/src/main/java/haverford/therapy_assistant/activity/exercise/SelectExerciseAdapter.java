package haverford.therapy_assistant.activity.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;
import haverford.therapy_assistant.data.Exercise;

public class SelectExerciseAdapter extends BaseAdapter implements ListAdapter {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    Vector<Exercise> exercises;

    public SelectExerciseAdapter(Context context, Vector<Exercise> ex){
        exercises = ex;
    }
    @Override
    public int getCount() {
        // TODO: Uncomment
        //return exercises.size();
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final Context context = parent.getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_selectexercise_list, parent, false);
        }
        final TextView tv = (TextView) view.findViewById(R.id.exercise_name);
        tv.setText(exercises.get(position).getName());
        view.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("exercise_arg", exercises.get(position));
                Intent i = new Intent(context, DoExercise.class);
                i.putExtras(bundle);

                context.startActivity(i);


        }
    });
    return view;

    }}

// TODO: Uncomment below.
        /*if (position >= 0 && position < 3 ){
            final Context context = parent.getContext();
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.activity_selectexercise_list, parent, false);
            }
            final TextView tv = (TextView) view.findViewById(R.id.exercise_name);
            // TODO: Uncomment
            //tv.setText(exercises.get(position).getName());
            tv.setText(Util.util_exercise.getName());
            tv.setOnClickListener(new View.OnClickListener(

            ) {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    // TODO: make a constants file for "exercise_arg" and other constants.
                    Exercise exercise = exercises.get(position);
                    // TODO: Uncomment below.
                    bundle.putSerializable("exercise_arg", Util.util_exercise );
                    Intent i = new Intent(context, DoExercise.class);
                    i.putExtras(bundle);
                    context.startActivity(i);

                }
            });

            return view;
        } else {
            return null;*/
