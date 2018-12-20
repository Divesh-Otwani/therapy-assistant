package haverford.therapy_assistant.activity.exercise;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observer;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.localstore.LocalStorage;
import haverford.therapy_assistant.util.Util;

public class Exercises extends AppCompatActivity {
    public static final String TITLE = "Exercises";
    private String TAG = "ViewDatabaseExercise";
    private Vector<Exercise> exercises;
    ExerciseListRecentAdapter mExerciseListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Util.makeToolbar(this, TITLE, R.id.exercises_toolbar);

        // Set up FAB
        FloatingActionButton doExercise = this.findViewById(R.id.exercises_fab);
        doExercise.setSize(FloatingActionButton.SIZE_AUTO);
        doExercise.setOnClickListener(Util.makeActStartListener(this, SelectExercise.class));


        // Initialize the ListView.
        exercises = new Vector<Exercise>();
        ListView lw = (ListView) findViewById(R.id.recent_list);
        mExerciseListAdapter = new ExerciseListRecentAdapter(exercises);
        collectAllExercises(this, exercises, mExerciseListAdapter);
        lw.setAdapter(mExerciseListAdapter);

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    public static void collectAllExercises(Context c, Vector<Exercise> exe, ExerciseListRecentAdapter adapter){
        exe = new Vector<>();
        LocalStorage localStorage = new LocalStorage(c);
        HashMap<Date,Vector<Exercise>> queryExercises = localStorage.queryExercises();
        Iterator<Map.Entry<Date, Vector<Exercise>>> entries = queryExercises.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Date, Vector<Exercise>> pair = entries.next();
            for (Exercise ex : pair.getValue()){
                ex.setDate(pair.getKey());
                exe.add(ex);
            }
        }
        Collections.sort(exe);
        adapter.tellObservers();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return Util.createOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return Util.optionItemSelected(this, item);
    }


}
