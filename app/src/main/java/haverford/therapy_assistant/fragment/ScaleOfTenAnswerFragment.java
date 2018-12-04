package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;

public class ScaleOfTenAnswerFragment extends AnswerFragment {

    private int currentAnswer=5;

    @Override
    public AnswerFragment newInstance() {
        AnswerFragment out = new ScaleOfTenAnswerFragment();

        return out;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.scale_of_ten_answer,container,false);

        SeekBar seekBar = v.findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentAnswer = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        return v;
    }

    @Override
    public Answer getAnswer() {
        return null;
    }
}
