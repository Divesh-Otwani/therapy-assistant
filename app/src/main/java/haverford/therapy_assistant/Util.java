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



import haverford.therapy_assistant.activity.exercise.Exercises;
import haverford.therapy_assistant.activity.resource.Resources;
import haverford.therapy_assistant.activity.exercise.SelectExercise;
import haverford.therapy_assistant.data.Resource;

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
    * */


    public static void makeToolbar(final AppCompatActivity act,
                                   String title, int toolbarID){
        // TODO: Change layout to be nicer
        Toolbar bar = act.findViewById(toolbarID);
        LinearLayout.LayoutParams toolbarLayout =
                new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        Toolbar.LayoutParams.WRAP_CONTENT);
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

            case R.id.menu_doexercise:
                Util.startActivity(act, SelectExercise.class);
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
    public static void buttonActivityStarter(final Context c,
                                             Button b,
                                             final Class activity){
        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c.startActivity(new Intent(c, activity));
                    }
                });

    }



}
