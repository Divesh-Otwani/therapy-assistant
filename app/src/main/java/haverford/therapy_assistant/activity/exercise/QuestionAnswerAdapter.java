package haverford.therapy_assistant.activity.exercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Question;

public class QuestionAnswerAdapter extends BaseAdapter implements ListAdapter {
    ArrayList<Question> qu;

    public QuestionAnswerAdapter(Context context, ArrayList<Question> q){
        qu = q;
    }

    @Override
    public int getCount() {

        return qu.size();
    }

    @Override
    public Object getItem(int position) {
        return qu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Question question = qu.get(position);
        final Context context = parent.getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.question_answer_list, parent, false);
        }
        TextView tv = (TextView) view.findViewById(R.id.question);
        tv.setText(question.getPrompt());
        /*
        ask how answer to question is shown and how to get the answer

         */

        // answer will always be a textview cause either a string, number or percentage
        TextView tv2 = (TextView) view.findViewById(R.id.answer);
        if(question.isAnswered()){
            tv2.setText(question.getAnswerString());
        }

        return view;
    }
}
