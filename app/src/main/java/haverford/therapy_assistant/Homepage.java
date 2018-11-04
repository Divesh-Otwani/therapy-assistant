package haverford.therapy_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;


import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.activity.exercise.Exercises;
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

        Button resources = this.findViewById(R.id.resources);
        Button exercises = this.findViewById(R.id.exercises);
        Toolbar bar = this.findViewById(R.id.home_toolbar);
        Util.makeToolbar(this, TITLE, bar);
        Util.buttonActivityStarter(this, resources, Resources.class);
        Util.buttonActivityStarter(this, exercises, Exercises.class);
        this.setSupportActionBar(bar);
        //this.getSupportActionBar().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
