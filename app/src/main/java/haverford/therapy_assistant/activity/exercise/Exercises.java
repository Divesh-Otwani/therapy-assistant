package haverford.therapy_assistant.activity.exercise;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.cloud.CloudData;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.localstore.LocalStorage;

public class Exercises extends AppCompatActivity {
    public static final String TITLE = "Exercises";
    private String TAG = "ViewDatabaseExercise";
    TextView tv;
    TableLayout table;
    private DatabaseReference ref;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Util.makeToolbar(this, TITLE, R.id.exercises_toolbar);


        // Set up FAB
        FloatingActionButton doExercise = this.findViewById(R.id.exercises_fab);
        doExercise.setSize(FloatingActionButton.SIZE_AUTO);
        doExercise.setOnClickListener(Util.makeActStartListener(this, SelectExercise.class));
        //CloudData cd = new CloudData();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        /*for(Exercise ex : cd.pullExercises()){
            Log.d(TAG, ex.toString());
            Log.d(TAG, "After exercise");
        }*/
        //Log.d(TAG, cd.pullExercises().toString());
        ListView lw = (ListView) findViewById(R.id.recent_list);
        ListAdapter la = new ExerciseListRecentAdapter(this);
        lw.setAdapter(la);
        ListView lw2 = (ListView) findViewById(R.id.last_week_list);
        ListAdapter la2 = new ExcerciseListLastWeekAdapter(this);
        lw.setAdapter(la2);
        ListView lw3 = (ListView) findViewById(R.id.other_list);
        ListAdapter la3 = new ExerciseListOtherAdapter(this);
        lw3.setAdapter(la3);


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
