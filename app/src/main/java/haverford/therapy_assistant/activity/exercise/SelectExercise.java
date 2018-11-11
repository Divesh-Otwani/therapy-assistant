package haverford.therapy_assistant.activity.exercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.Util;

public class SelectExercise extends AppCompatActivity{
    private static final String TITLE="Select Exercise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectexercise);
        Util.makeToolbar(this, TITLE, R.id.selectexercise_toolbar);

        // TODO: remove this code when time
        TextView v = this.findViewById(R.id.testTextView);
        v.setOnClickListener(Util.makeActStartListener(this, DoExercise.class));

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
