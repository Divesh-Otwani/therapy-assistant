package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.TextAnswer;

public class TextAnswerFragment extends AnswerFragment {

    private String mAnswer;

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

        final EditText edit = v.findViewById(R.id.editTextAnswer);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAnswer = edit.getText().toString();
            }
        });

        return v;
    }

    @Override
    public Answer getAnswer() {
        return new TextAnswer(mAnswer);
    }
}
