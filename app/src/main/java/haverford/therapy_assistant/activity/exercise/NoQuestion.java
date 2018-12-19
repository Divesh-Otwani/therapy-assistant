package haverford.therapy_assistant.activity.exercise;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;

public class NoQuestion extends AppCompatActivity {
    private static final String TITLE="Select An Exercise";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_question);
        //Util.makeToolbar(this, TITLE, R.id.selectexercise_toolbar);



    }

}
