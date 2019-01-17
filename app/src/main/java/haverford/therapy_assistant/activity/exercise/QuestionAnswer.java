package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.util.Util;
import haverford.therapy_assistant.data.Question;

public class QuestionAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer_activity);

        // Get intent extras: list of questions,
        Intent creator = this.getIntent();
        Bundle bundle = creator.getExtras();
        ArrayList<Question> mQuestions = (ArrayList<Question>) bundle.getSerializable("recent_list_arg");
        String exerciseName = bundle.getString("exercise_name");

        // Toolbar
        Util.makeToolbar(this, exerciseName, R.id.exercises_toolbar);

        // Setup the list view.
        ListView lv = (ListView) findViewById(R.id.questionanswer_list);
        ListAdapter ad = new QuestionAnswerAdapter(this, mQuestions);
        lv.setAdapter(ad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return Util.createOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return Util.optionItemSelected(this, item);
    }
}
