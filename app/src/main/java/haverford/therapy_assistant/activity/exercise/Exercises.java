package haverford.therapy_assistant.activity.exercise;

import android.os.Bundle;
import android.app.Activity;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.cloud.CloudData;

public class Exercises extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        CloudData cd = new CloudData();
        cd.getii();
    }

}
