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

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;

public class Resource extends AppCompatActivity {

    private static final String TITLE = "CBT Resources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_resources);

        Util.makeToolbar(this, TITLE, R.id.cbt_toolbar);


        String[] testResources = {"Articles","https://www.nytimes.com/2018/06/13/well/cognitive-behavior-therapy-suicide.html","Videos","youtube.com"};

        ListView listView = this.findViewById(R.id.cbt_list);
        listView.setAdapter(new ResourceAdapter(testResources,this));

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

