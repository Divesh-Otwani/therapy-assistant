package haverford.therapy_assistant.activity.resource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;

public class ResourceHome extends AppCompatActivity {

    private static final String TITLE = "Resources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);


        Button actButton = this.findViewById(R.id.grid_button_act);
        Button cbtButton = this.findViewById(R.id.grid_button_cbt);

        Util.makeToolbar(this, TITLE, R.id.resource_toolbar);
        Util.buttonActivityStarter(this,cbtButton,Resource.class);

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