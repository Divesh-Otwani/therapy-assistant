package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;

public class TextAnswerFragment extends AnswerFragment {
    @Override
    public AnswerFragment newInstance() {
        AnswerFragment out = new TextAnswerFragment();

        return out;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.text_answer,container,false);


        return v;
    }

    @Override
    public Answer getAnswer() {
        return null;
    }
}
