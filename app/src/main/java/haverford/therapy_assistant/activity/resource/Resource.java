package haverford.therapy_assistant.activity.resource;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;

public class Resource extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_resources);

        // TODO: Open this activity with an argument for the resource category
        // Use it below.
        Util.makeToolbar(this, "Some Resource Category", R.id.cbt_toolbar);


        // TODO: Using the database, populate the listview.
        ListView listView = this.findViewById(R.id.cbt_list);
        listView.setAdapter(new ResourceListAdapter(new ArrayList(),this));

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

