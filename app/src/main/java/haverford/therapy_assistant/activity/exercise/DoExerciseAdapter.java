package haverford.therapy_assistant.activity.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.fragment.AnswerFragment;
import haverford.therapy_assistant.fragment.TextAnswerFragment;

public class DoExerciseAdapter extends FragmentPagerAdapter {

    private Exercise mExercise;

    public DoExerciseAdapter(FragmentManager fm, Exercise exercise) {
        super(fm);
        mExercise = exercise;
    }


    public AnswerFragment getCurrFragment(){
        return new TextAnswerFragment(); // For now.
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        return new TextAnswerFragment();
    }

    @Override
    public int getCount() {
        return mExercise.getQuestions().size();
    }
}
