package haverford.therapy_assistant.activity.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.Util;
import haverford.therapy_assistant.data.Question;

public class QuestionAnswer extends AppCompatActivity {
    public static final String TITLE = "QuestionAnswer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_answer_activity);
        Util.makeToolbar(this, TITLE, R.id.exercises_toolbar);



        FloatingActionButton doExercise = this.findViewById(R.id.exercises_fab);
        doExercise.setSize(FloatingActionButton.SIZE_AUTO);
        doExercise.setOnClickListener(Util.makeActStartListener(this, SelectExercise.class));
        Intent i = getIntent();
        ListView lv = (ListView) findViewById(R.id.questionanswer_list);
        ListAdapter ad = new QuestionAnswerAdapter(this, (Vector<Question>)i.getSerializableExtra("questions"));
        lv.setAdapter(ad);
        //CloudData cd = new CloudData();



        /*for(Exercise ex : cd.pullExercises()){
            Log.d(TAG, ex.toString());
            Log.d(TAG, "After exercise");
        }*/
        //Log.d(TAG, cd.pullExercises().toString());



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return Util.createOptionsMenu(this, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return Util.optionItemSelected(this, item);
    }
}
