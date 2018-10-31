package haverford.therapy_assistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

}
