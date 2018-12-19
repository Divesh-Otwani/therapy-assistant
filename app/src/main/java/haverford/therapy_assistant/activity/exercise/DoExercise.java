package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.MultipleChoiceAnswer;
import haverford.therapy_assistant.data.answer.PercentageAnswer;
import haverford.therapy_assistant.data.answer.ScaleOfTenAnswer;
import haverford.therapy_assistant.data.answer.TextAnswer;
import haverford.therapy_assistant.fragment.AnswerFragment;
import haverford.therapy_assistant.fragment.PercentageAnswerFragment;
import haverford.therapy_assistant.fragment.ScaleOfTenAnswerFragment;
import haverford.therapy_assistant.fragment.TextAnswerFragment;
import haverford.therapy_assistant.localstore.LocalStorage;

public class DoExercise extends AppCompatActivity {

    // The android pager
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

        // Get the Exercise in the intent. Set title.
        Intent creator = this.getIntent();
        mExercise = (Exercise) creator.getExtras().getSerializable("exercise_arg");
        Log.d(TAG, "mExercise " + mExercise.getName() + " " + mExercise.getQuestions());
        mLength = mExercise.getQuestions().size();
        mQuestionText = (TextView) findViewById(R.id.do_exercise_question);
        TextView title = (TextView) findViewById(R.id.do_exercise_title);
        title.setText(mExercise.getName()); // TODO: set font size.

        // Show the toolbar with forward/save and back button.
        setupBottomNavigation();

        // Create the adapter that will return a fragment for each
        // question in the exercise. Then attach this adapter.
        mPageAdapter = new DoExerciseAdapter(getSupportFragmentManager(), mExercise);
        mViewPager = (ViewPager) findViewById(R.id.do_exercise_response);
        mViewPager.setAdapter(mPageAdapter);

        updateUI();
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


    private void setupBottomNavigation(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.do_exercise_toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setBackgroundColor(Color.rgb(30, 136, 229));
        setupEvenlyDistributedToolbar(toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().show();
    }

    private Answer selectFragment(QuestionType ty, String cs){
        switch (ty){
            case MultipleChoiceAnswer:
                return new MultipleChoiceAnswer();
            case PercentageAnswer:
                return new PercentageAnswer(Integer.valueOf(cs));
            case TextAnswer:
                return new TextAnswer((String) cs);
            case ScaleOfTenAnswer:
                return new ScaleOfTenAnswer(Integer.valueOf(cs));
        }
        return null; // Crash!
    }

    private void saveAnswer(){
        AnswerFragment currFragment = (AnswerFragment) mPageAdapter.getItem(mPtr);

        Answer ans = currFragment.getAnswer();
        Log.d("Tag", "Answer: " + ans.toString());

        Log.d("Tag", "BEFORE ...");
        Log.d("Tag", mExercise.getQuestions().get(mPtr).toString());
        mExercise.getQuestions().get(mPtr).answerQuestion(ans); // This mutates state right?
        Log.d("Tag","AFTER ...");
        Log.d("Tag", mExercise.getQuestions().get(mPtr).toString());
        Log.d("Tag","\n \n Done ... \n \n");

        //EditText ed = (EditText) findViewById(R.id.editTextAnswer);
        //String eds = ed.getText().toString();
        //mPageAdapter.getCurrFragment(getCurrQuestion().getQType()).setmAnswer(ed.getText().toString());
                //getCurrFragment(getCurrQuestion().getQType()).getAnswer();
        // Log.d("DoExercise saveAnswer()", ed.getText().toString());
        //getCurrQuestion().answerQuestion(ans);
        //getCurrQuestion().answerQuestion(selectFragment(getCurrQuestion().getQType(), eds));
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


    /*
     * Evenly distribute toolbar items. Source:
     * https://stackoverflow.com/questions/26489079/evenly-spaced-menu-items-on-toolbar
     */
    public void setupEvenlyDistributedToolbar(Toolbar toolbar){
        // Use Display metrics to get Screen Dimensions
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // Inflate your menu
        toolbar.inflateMenu(R.menu.menu_do_exercise);

        // Add 10 spacing on either side of the toolbar
        toolbar.setContentInsetsAbsolute(10, 10);

        // Get the ChildCount of your Toolbar, this should only be 1
        int childCount = toolbar.getChildCount();
        // Get the Screen Width in pixels
        int screenWidth = metrics.widthPixels;

        // Create the Toolbar Params based on the screenWidth
        Toolbar.LayoutParams toolbarParams = new
                Toolbar.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Loop through the child Items
        for(int i = 0; i < childCount; i++){
            // Get the item at the current index
            View childView = toolbar.getChildAt(i);
            // If its a ViewGroup
            if(childView instanceof ViewGroup){
                // Set its layout params
                childView.setLayoutParams(toolbarParams);
                // Get the child count of this view group, and compute the item
                // widths based on this count & screen size
                int innerChildCount = ((ViewGroup) childView).getChildCount();
                int itemWidth  = (screenWidth / innerChildCount);
                // Create layout params for the ActionMenuView
                ActionMenuView.LayoutParams params = new
                        ActionMenuView.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                // Loop through the children
                for(int j = 0; j < innerChildCount; j++){
                    View grandChild = ((ViewGroup) childView).getChildAt(j);
                    if(grandChild instanceof ActionMenuItemView){
                        // set the layout parameters on each View
                        grandChild.setLayoutParams(params);
                    }
                }
            }
        }
    }
}





