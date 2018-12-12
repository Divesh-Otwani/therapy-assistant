package haverford.therapy_assistant.activity.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.fragment.AnswerFragment;
import haverford.therapy_assistant.fragment.PercentageAnswerFragment;
import haverford.therapy_assistant.fragment.ScaleOfTenAnswerFragment;
import haverford.therapy_assistant.fragment.TextAnswerFragment;

public class DoExerciseAdapter extends FragmentPagerAdapter {

    private Exercise mExercise;


    public DoExerciseAdapter(FragmentManager fm, Exercise exercise) {
        super(fm);
        mExercise = exercise;
    }

    private QuestionType getCurrQuesType(int position){
        return mExercise.getQuestions().get(position).getQType();
    }

    private AnswerFragment selectFragment(QuestionType ty){
        switch (ty){
            case PercentageAnswer:
                return new PercentageAnswerFragment();
            case TextAnswer:
                return new TextAnswerFragment();
            case ScaleOfTenAnswer:
                return new ScaleOfTenAnswerFragment();
        }
        return null; // Crash!
    }

    public AnswerFragment getCurrFragment(){
        return new TextAnswerFragment(); // For now.
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return selectFragment(getCurrQuesType(position));
    }

    @Override
    public int getCount() {
        return mExercise.getQuestions().size();
    }




}
