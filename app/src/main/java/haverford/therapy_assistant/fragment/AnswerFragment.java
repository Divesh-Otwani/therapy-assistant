package haverford.therapy_assistant.fragment;

import android.support.v4.app.Fragment;

import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.answer.Answer;

public abstract class AnswerFragment extends Fragment {

    public AnswerFragment() {super();}

    public abstract AnswerFragment newInstance();
    public abstract Answer getAnswer();
}
