package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.localstore.LocalStorage;

public class DoExercise extends AppCompatActivity {

    // The android pager
    private DoExerciseAdapter mPageAdapter;
    private ViewPager mViewPager;

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
        LocalStorage l = new LocalStorage(this);
        Date currDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        l.storeExercise(currDate, mExercise);
    }

    private void goToExercisesPage(){
        this.startActivity(new Intent(this, Exercises.class));
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
                if (mPtr == 0){
                    return true;
                } else {
                    mPtr -= 1;
                    updateUI();
                    return true;
                }
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





