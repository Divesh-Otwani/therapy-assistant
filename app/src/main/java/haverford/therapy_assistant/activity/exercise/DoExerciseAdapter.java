package haverford.therapy_assistant.activity.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.fragment.AnswerFragment;
import haverford.therapy_assistant.fragment.MultipleChoiceAnswerFragment;
import haverford.therapy_assistant.fragment.PercentageAnswerFragment;
import haverford.therapy_assistant.fragment.ScaleOfTenAnswerFragment;
import haverford.therapy_assistant.fragment.TextAnswerFragment;

public class DoExerciseAdapter extends FragmentPagerAdapter {

    private Exercise mExercise;
    private Vector<AnswerFragment> mFrags = new Vector();


    public DoExerciseAdapter(FragmentManager fm, Exercise exercise) {
        super(fm);
        mExercise = exercise;
        int numQuestions = exercise.getQuestions().size();
        for (int i = 0; i< numQuestions; ++i){
            mFrags.add(null);
        }
    }

    private QuestionType getCurrQuesType(int position){
        return mExercise.getQuestions().get(position).getQType();
    }

    private AnswerFragment selectFragment(QuestionType ty){
        switch (ty){
            case MultipleChoiceAnswer:
                return new MultipleChoiceAnswerFragment();
            case PercentageAnswer:
                return new PercentageAnswerFragment();
            case TextAnswer:
                return new TextAnswerFragment();
            case ScaleOfTenAnswer:
                return new ScaleOfTenAnswerFragment();
        }
        return null; // Crash!
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        AnswerFragment curr = mFrags.get(position);
        if (curr != null){
            return curr;
        } else {
            AnswerFragment newFragment = selectFragment(getCurrQuesType(position));
            mFrags.setElementAt(newFragment, position);
            return newFragment;
        }
    }

    @Override
    public int getCount() {
        return mExercise.getQuestions().size();
    }




}
