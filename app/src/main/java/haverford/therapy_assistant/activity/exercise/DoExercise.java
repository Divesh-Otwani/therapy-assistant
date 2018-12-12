package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.answer.Answer;

public class DoExercise extends AppCompatActivity {

    // The response android pager
    private DoExerciseAdapter mPageAdapter;
    private ViewPager mViewPager;
    final String TAG = "DoExerciseTAG";
    // The state
    private int mLength;
    private int mPtr = 0;
    private Exercise mExercise;
    private TextView mQuestionText;


    /*PRECONDITIONS: non zero list of questions.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout
        setContentView(R.layout.activity_do_exercise);

        // Get the Exercise in the intent.
        Intent creator = this.getIntent();
        mExercise = (Exercise) creator.getExtras().getSerializable("exercise_arg");
        Log.d(TAG, "mExercise " + mExercise.getName() + " " + mExercise.getQuestions());
        mLength = mExercise.getQuestions().size();
        mQuestionText = (TextView) findViewById(R.id.do_exercise_question);


        // Show the toolbar with forward/save and back button.
        setupBottomNavigation();

        mPageAdapter = new DoExerciseAdapter(getSupportFragmentManager(), mExercise);
        mViewPager = (ViewPager) findViewById(R.id.do_exercise_response);
        mViewPager.setAdapter(mPageAdapter);

        updateUI();



        // Create the adapter that will return a fragment for each
        // question in the exercise. Then attach this adapter.



    }


    /*  Updating UI & Making Changes */

    private Question getCurrQuestion(){
        Log.d(TAG, mExercise.getQuestions().toString());
        Log.d(TAG, mExercise.getQuestions().get(mPtr).getPrompt());
        Log.d(TAG, "mPtr " + mPtr);
        return mExercise.getQuestions().elementAt(mPtr);
    }


    private void updateUI(){
        Question newQ = getCurrQuestion();
        String newQPrompt = newQ.getPrompt();
        mQuestionText.setText(newQPrompt);
        mViewPager.setCurrentItem(mPtr);
    }


    /*  Setting up the bottom navigation menu.  */

    private void setupBottomNavigation(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.do_exercise_toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setPopupTheme(R.style.AppTheme);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().show();
    }

    private void saveAnswer(){
        Answer ans = mPageAdapter.getCurrFragment().getAnswer();
        getCurrQuestion().answerQuestion(ans);
    }

    private void saveExercise(){

    }

    private void goToExercisesPage(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_do_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.do_exercise_back:
                mPtr -= 1;
                updateUI();
                return true;
            case R.id.do_exercise_forward_or_save:
                if (atLastPage()){
                    saveAnswer();
                    saveExercise();
                    goToExercisesPage();
                } else {
                    saveAnswer();
                    mPtr += 1;
                    updateUI();
                }
                return true;
        }
        // Set up the buttons in the do_exercise menu (back or forward/complete).
        return false;
    }


    /* Util */

    private boolean atLastPage(){
        return mPtr + 1 == mLength;
    }

}





