package haverford.therapy_assistant.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.PercentageAnswer;

public class PercentageAnswerFragment extends AnswerFragment {

    private int currentAnswer;

    @Override
    public AnswerFragment newInstance() {
        AnswerFragment out = new PercentageAnswerFragment();

        return out;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.percentage_answer,container,false);

        final TextView textVal = v.findViewById(R.id.percentValue);

        SeekArc arc = v.findViewById(R.id.seekArc);

        arc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                currentAnswer = i;
                textVal.setText(currentAnswer);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {

            }
        });


        return v;
    }

    @Override
    public Answer getAnswer() {
        return new PercentageAnswer(currentAnswer);
    }
}
