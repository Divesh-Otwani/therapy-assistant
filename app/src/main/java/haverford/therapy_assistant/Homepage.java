package haverford.therapy_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.activity.exercise.Exercises;
import haverford.therapy_assistant.activity.resource.Resources;

public class Homepage extends AppCompatActivity {
    private static final String TITLE="Therapy Assistant";


    /**
     * Set up the main page: add the title, and make the buttons active.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(TITLE);
        setContentView(R.layout.activity_homepage);

        // The image is somehow broken!!!
        ImageView image = this.findViewById(R.id.home_image1);
        image.setImageResource(R.drawable.ic_launcher_foreground);

        Button resources = this.findViewById(R.id.resources);
        Button exercises = this.findViewById(R.id.exercises);
        Util.buttonActivityStarter(this, resources, Resources.class);
        Util.buttonActivityStarter(this, exercises, Exercises.class);


    }

}
