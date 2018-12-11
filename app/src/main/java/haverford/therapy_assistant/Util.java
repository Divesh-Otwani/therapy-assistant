package haverford.therapy_assistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

import haverford.therapy_assistant.activity.exercise.Exercises;
import haverford.therapy_assistant.activity.resource.Resources;
import haverford.therapy_assistant.activity.exercise.SelectExercise;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.TextAnswer;

/**
 * A collection of top level android utilities to avoid repeating code.
 */
public class Util {

    public static Question util_q1 = new Question(
            0
            , QuestionType.TextAnswer
            ,"What ATs are you having?"
            , "AT identification");
    public static Question util_q2 = new Question(
            1
            , QuestionType.ScaleOfTenAnswer
            ,"Rate your anxiety right now."
            , "Anxiety Rating.");
    private static Question[] util_arr_qs = {util_q1, util_q2};
    public static Vector<Question> util_ques = new Vector(Arrays.asList(util_arr_qs));
    public static Exercise util_exercise = new Exercise(0,"SomeExerciseNm", util_ques);

    /* Toolbar Utilities
    *
    *
    *   Utilities to make a toolbar with a menu.
    *   These should be used as they are in the Homepage activity.
    *
    *  PRECONDITION: The top level layout of the activity must be a LinearLayout.
    *
    * */


    public static void makeToolbar(final AppCompatActivity act,
                                   String title, int toolbarID){
        // TODO: Change layout to be nicer
        Toolbar bar = act.findViewById(toolbarID);
        LinearLayout.LayoutParams toolbarLayout =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        bar.setLayoutParams(toolbarLayout);
        bar.setPopupTheme(R.style.AppTheme);
        bar.setVisibility(View.VISIBLE);
        bar.setTitle(title);
        act.setSupportActionBar(bar);
    }

    public static boolean createOptionsMenu(AppCompatActivity act,
                                            Menu menu){
        act.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public static boolean optionItemSelected(AppCompatActivity act,
                                      MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_resources:
                Util.startActivity(act, Resources.class);
                return true;

            case R.id.menu_exercises:
                Util.startActivity(act, Exercises.class);
                return true;

        }
        return false;
    }




   /* General Utilities
   *
   *
   */

    // Simple activity starter.
    public static void startActivity(final AppCompatActivity act,
                                     final Class activityToStart
                                     ){
        act.startActivity(new Intent(act, activityToStart));
    }

    // Precondition: activity needs to be an activity class!
    public static void buttonActivityStarter(final AppCompatActivity act,
                                             Button b,
                                             final Class activity){
        b.setOnClickListener(Util.makeActStartListener(act, activity));

    }

    public static View.OnClickListener makeActStartListener(final AppCompatActivity act,
                                                            final Class activityToStart){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    Util.startActivity(act, activityToStart);
            }
        };
    }


}
