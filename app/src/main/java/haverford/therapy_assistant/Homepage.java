package haverford.therapy_assistant;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;


import haverford.therapy_assistant.activity.exercise.ExerciseListRecentAdapter;
import haverford.therapy_assistant.util.Util;
import haverford.therapy_assistant.activity.exercise.SelectExercise;

public class Homepage extends AppCompatActivity {
    private static final String TITLE="Therapy Assistant";


    /**
     * Set up the main page: add the title, and make the buttons active.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Util.makeToolbar(this, TITLE, R.id.home_toolbar);

        // Set up FAB
        FloatingActionButton doExercise = this.findViewById(R.id.exercises_fab_home);
        doExercise.setSize(FloatingActionButton.SIZE_AUTO);
        doExercise.setOnClickListener(Util.makeActStartListener(this, SelectExercise.class));

        ListView lw = (ListView) findViewById(R.id.recent_list_homepage);
        ListAdapter la = new ExerciseListRecentAdapter(this);
        lw.setAdapter(la);

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
