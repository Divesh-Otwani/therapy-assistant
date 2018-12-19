package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.MultipleChoiceAnswer;
import haverford.therapy_assistant.data.answer.ScaleOfTenAnswer;

public class MultipleChoiceAnswerFragment extends AnswerFragment {

    private String[] mPrompts;
    private String mResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrompts = (String[]) getArguments().getCharSequenceArray("prompts");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.multiple_choice_answer,container,false);

        RadioGroup radioGroup = v.findViewById(R.id.choiceRadio);
        int i = 0;
        for (String prompt : mPrompts) {
            RadioButton radiobutton=new RadioButton(getContext());
            radiobutton.setText(prompt);
            radiobutton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radiobutton.setId(i++);
            radioGroup.addView(radiobutton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = v.findViewById(checkedId);

                mResponse = (String) button.getText();

            }
        });

        return v;
    }

    @Override
    public AnswerFragment newInstance() {
        return newInstance(null);
    }
    public static AnswerFragment newInstance(String[] prompts) {
        AnswerFragment out = new MultipleChoiceAnswerFragment();

        Bundle args = new Bundle();
        args.putCharSequenceArray("prompts",prompts);

        return out;
    }

    @Override
    public Answer getAnswer() {
        return new MultipleChoiceAnswer(mPrompts).setResponse(mResponse);
    }
}
