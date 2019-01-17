package haverford.therapy_assistant.activity.resource;

import android.app.Activity;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.resource.Resource;

/* Right now this assumes only one type of resource and displays links.
* Later, it will take cases on the type of resource and handle them appropreately in
* getView. */

class ResourceListAdapter extends BaseAdapter implements ListAdapter {

    ArrayList<Resource> mList;
    Activity mAct;


    protected ResourceListAdapter(ArrayList<Resource> in, Activity cont) {
        mList = in;
        mAct = cont;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Resource getItem(int position) {
        return (Resource) mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Resource) this.getItem(position)).getUID();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = mAct.getLayoutInflater().inflate(R.layout.cbt_list_item, parent, false);
        }

        // Show each resource.
        ((TextView) view.findViewById(R.id.cbt_res_text)).setText(getItem(position).getRestype().toString());
        ((TextView) view.findViewById(R.id.cbt_res_text2)).setText(getItem(position).getTitle());
        ((TextView) view.findViewById(R.id.cbt_res_text3)).setText(getItem(position).getName());
        Linkify.addLinks((TextView) view.findViewById(R.id.cbt_res_text3), Linkify.WEB_URLS);
        return view;
    }
}