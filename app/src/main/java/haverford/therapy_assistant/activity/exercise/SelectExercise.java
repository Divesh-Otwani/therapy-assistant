package haverford.therapy_assistant.activity.exercise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        final Context self = this;
        TextView v = this.findViewById(R.id.testTextView);
        v.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent i = new Intent(self, DoExercise.class);
                                     Bundle b = new Bundle();
                                     b.putSerializable("exercise_arg", Util.util_exercise);
                                     i.putExtras(b);
                                     startActivity(i);
                                 }
                             }
        );

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
