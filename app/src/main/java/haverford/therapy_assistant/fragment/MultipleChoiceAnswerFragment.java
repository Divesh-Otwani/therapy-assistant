package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.ScaleOfTenAnswer;

public class MultipleChoiceAnswerFragment extends AnswerFragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.multiple_choice_answer,container,false);

        return v;
    }

    @Override
    public AnswerFragment newInstance() {
        return newInstance(null);
    }
    public AnswerFragment newInstance(String[][] prompts) {
        AnswerFragment out = new MultipleChoiceAnswerFragment();

        return out;
    }

    @Override
    public Answer getAnswer() {
        return null;
    }
}
