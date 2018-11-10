package haverford.therapy_assistant.activity.resource;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.activity.exercise.Exercises;


/* Zach should flush this part out. Make this activity pretty and make
 a few other activities. Talk to Yasmine about pulling online data.
 See the Resource class in the data package -- that's yours to work on.
*/

public class Resources extends AppCompatActivity {

    private static final String TITLE = "Resources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        Toolbar bar = this.findViewById(R.id.resource_toolbar);
        Util.makeToolbar(this, TITLE, bar);
        this.setSupportActionBar(bar);

        Button actButton = this.findViewById(R.id.grid_button_act);
        Button cbtButton = this.findViewById(R.id.grid_button_cbt);

        Util.buttonActivityStarter(this,cbtButton,CBTResourceAdapter.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.resource_menu, menu);
        return true;
    }
}