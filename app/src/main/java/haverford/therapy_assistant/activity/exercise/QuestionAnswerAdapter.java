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
    ArrayList<Question> questions;

    public QuestionAnswerAdapter(Context context, ArrayList<Question> q){
        questions = q;
    }

    @Override
    public int getCount() { return questions.size(); }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Question question = questions.get(position);
        final Context context = parent.getContext();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.question_answer_list, parent, false);
        }
        // Display prompt and answer in textviews.
        TextView questionPrompt = (TextView) view.findViewById(R.id.question);
        questionPrompt.setText(question.getPrompt());
        TextView answer = (TextView) view.findViewById(R.id.answer);
        if(question.isAnswered()){
            answer.setText(question.getAnswerString());
        }
        return view;
    }
}
