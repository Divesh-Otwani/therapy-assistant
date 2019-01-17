package haverford.therapy_assistant.util;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;


import java.util.Arrays;
import java.util.Vector;

import haverford.therapy_assistant.Homepage;
import haverford.therapy_assistant.R;
import haverford.therapy_assistant.activity.exercise.Exercises;
import haverford.therapy_assistant.activity.resource.ResourceHome;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;

/**
 * A collection of top level android utilities to avoid repeating code.
 */
public class Util {


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
        bar.setBackgroundColor(Color.rgb(30, 136, 229));
        bar.setTitleTextColor(Color.WHITE);
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
            case R.id.menu_home:
                Util.startActivity(act, Homepage.class);
                return true;

            case R.id.menu_resources:
                Util.startActivity(act, ResourceHome.class);
                return true;

            case R.id.menu_exercises:
                Util.startActivity(act, Exercises.class);
                return true;

        }
        return false;
    }




   /* General Utilities */

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
