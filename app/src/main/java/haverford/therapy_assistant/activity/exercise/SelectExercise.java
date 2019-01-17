package haverford.therapy_assistant.activity.exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Vector;

import haverford.therapy_assistant.R;
import haverford.therapy_assistant.util.Util;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;

public class SelectExercise extends AppCompatActivity{

    private static final String TITLE="Select An Exercise";
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectexercise);
        Util.makeToolbar(this, TITLE, R.id.selectexercise_toolbar);

        // Setup database
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();


        // Setup list of exercises


        //
        asyncSetupListviewAdapter();
    }



    private void asyncSetupListviewAdapter() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot exer = dataSnapshot.child("Exercise");
                DataSnapshot ques = dataSnapshot.child("Question");
                HashMap<Integer, Question> hm = new HashMap<>();

                Vector<Exercise> ex = new Vector<>();
                for(DataSnapshot uniqueChild : ques.getChildren()){
                    Integer id = -1;
                    String name = "";
                    String text = "";
                    Integer qtype = -1;
                    for(DataSnapshot unique : uniqueChild.getChildren()){
                        if(unique.getKey().equals("Name")){
                            name = (String) unique.getValue();
                        }else if(unique.getKey().equals("Qtype")){
                            qtype = ((Long) unique.getValue()).intValue();
                        }else if(unique.getKey().equals("Text")){
                            text = (String) unique.getValue();
                        }else if(unique.getKey().equals("UID")){
                            id = ((Long) unique.getValue()).intValue();
                        }
                    }
                    hm.put(id, new Question(id, QuestionType.values()[qtype], text, name));

                }

                for(DataSnapshot uniqueExer : exer.getChildren()){
                    String name = "";
                    Integer uid = -1;
                    Vector<Question> vecQuestions = new Vector<Question>();
                    for(DataSnapshot unique : uniqueExer.getChildren()){
                        if(unique.getKey().equals("Name")){
                            name = (String) unique.getValue();

                        }else if(unique.getKey().equals("UID")){
                            uid = ((Long) unique.getValue()).intValue();
                        }else{
                            for(DataSnapshot u : unique.getChildren()){
                                vecQuestions.add(hm.get(((Long) u.getValue()).intValue()));
                            }
                        }

                    }
                    ex.add(new Exercise(uid, name, vecQuestions));

                }
                ListView exercisesToChooseFrom = (ListView) findViewById(R.id.selectexercise_list);
                ListAdapter exerciseAdapter = new SelectExerciseAdapter(SelectExercise.this, ex);
                exercisesToChooseFrom.setAdapter(exerciseAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
