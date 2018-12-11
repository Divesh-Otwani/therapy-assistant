package haverford.therapy_assistant;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;


import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.activity.exercise.Exercises;
import haverford.therapy_assistant.activity.exercise.SelectExercise;
import haverford.therapy_assistant.activity.resource.Resources;

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
