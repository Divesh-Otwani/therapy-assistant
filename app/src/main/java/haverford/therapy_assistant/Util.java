package haverford.therapy_assistant;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import org.xmlpull.v1.XmlPullParser;

import java.util.jar.Attributes;

/**
 * A collection of top level android utilities to avoid repeating code.
 */
public class Util {


    // Precondition: activity needs to be an activity class!
    public static void buttonActivityStarter(final Context c, Button b, final Class activity){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(c, activity));
            }
        });

    }


    public static void makeToolbar(final Context c, String title, Toolbar toolbar){
        // TODO: Change layout to be nicer
        LinearLayout.LayoutParams toolbarLayout =
                new LinearLayout.LayoutParams(
                        Toolbar.LayoutParams.MATCH_PARENT,
                        Toolbar.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(toolbarLayout);
        toolbar.setPopupTheme(R.style.AppTheme);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
    }






}
