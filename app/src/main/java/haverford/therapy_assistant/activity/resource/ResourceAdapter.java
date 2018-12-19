package haverford.therapy_assistant.activity.resource;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import haverford.therapy_assistant.R;


class ResourceAdapter extends BaseAdapter implements ListAdapter {

    String[] mList;
    Activity mAct;


    protected ResourceAdapter(String[] in, Activity cont) {
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