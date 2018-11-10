package haverford.therapy_assistant.activity.resource;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.activity.exercise.Exercises;

public class CBTResources extends AppCompatActivity {

    private static final String TITLE = "CBT Resources";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_resources);

        Toolbar bar = this.findViewById(R.id.cbt_toolbar);
        Util.makeToolbar(this, TITLE, bar);
        this.setSupportActionBar(bar);

        String[] testResources = {"Articles","https://www.nytimes.com/2018/06/13/well/cognitive-behavior-therapy-suicide.html","Videos","youtube.com"};

        ListView listView = this.findViewById(R.id.cbt_list);
        listView.setAdapter(new CBTResourceAdapter(testResources,this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.resource_menu, menu);
        return true;
    }
}


class CBTResourceAdapter extends BaseAdapter implements ListAdapter {

    String[] mList;
    Activity mAct;


    protected CBTResourceAdapter(String[] in, Activity cont) {
        mList = in;
        mAct = cont;
    }

    @Override
    public int getCount() {
        return mList.length;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mAct.getLayoutInflater().inflate(R.layout.cbt_list_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.cbt_res_text))
                .setText((String)getItem(position));
        return convertView;
    }
}