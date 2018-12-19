package haverford.therapy_assistant.cloud;

// Yasmine should work primiarly in this package
// You can make constructors that take whatever inputs you choose

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Vector;

import haverford.therapy_assistant.data.resource.ArticleResource;
import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.resource.Resource;

public class CloudData {
    private String TAG = "ViewDatabase";
    private FirebaseDatabase database;
    private DatabaseReference ref;


    public CloudData() {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        /*ref.child("Exercise").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        //Vector<Exercise> ve = new Vector<Exercise>();
        //pullExercises();
        //Log.d(TAG, "in constructor pullexercise" + ve);
    }


    public void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
            for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){
            //Loop 1 to go through all the child nodes of users
            Log.d(TAG, "Key: " + unique.getKey());
            Log.d(TAG, "Value: " + unique.getValue());
            System.out.print(unique.getKey());
            System.out.print(unique.getValue());
        }}
    }


    // Implement this function
    // Have a few test exercises in there for now.
    public Vector<Exercise> pullExercises(){
        final Vector<Exercise> ve = new Vector<Exercise>();
        final HashMap<Integer, Question> hm = new HashMap<>();
        final int[] id2 = new int[1];

        ref.child("Question").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    String name = "";
                    String prompt= "";
                    QuestionType qt = null;
                    for (DataSnapshot unique : uniqueKeySnapshot.getChildren()) {
                        if (unique.getKey() == "Name") {
                            name = (String)unique.getValue();
                        } else if (unique.getKey() == "UID") {
                            id2[0] = (int)unique.getValue();
                        } else if(unique.getKey() == "Qtype"){
                            qt = QuestionType.values()[(int)unique.getValue()];
                        }else if(unique.getKey() == "Text"){
                            prompt = (String)unique.getValue();
                        }
                        hm.put(id2[0], new Question(id2[0], qt, prompt, name));
                    }


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT Question??: " + String.valueOf(databaseError));
            }
        });

        ref.child("Exercise").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "In exercise");
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    int id = -1;
                    String name = "";
                    Vector<Question> vq = new Vector<Question>();
                    for (DataSnapshot unique : uniqueKeySnapshot.getChildren()) {
                        if (unique.getKey() == "Name") {
                            name = (String)unique.getValue();
                        } else if (unique.getKey() == "UID") {
                            id = (int)unique.getValue();
                        } else {
                            for (DataSnapshot i : unique.getChildren()) {
                                if (i.getValue() != null) {
                                    vq.add(hm.get(i.getValue()));
                                }
                            }
                        }
                        ve.add(new Exercise(id, name, vq));
                    }

                }
                Log.d(TAG, "What ve is" + ve.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
            }

        });
        Log.d(TAG, "What ve is when trying to return " + ve.toString());
        return ve;
    }


    // Also implement this
    public Vector<Resource> pullResources(){
        final Vector<Resource> qu = new Vector<Resource>();
        ref.child("Resource").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    String name = "";
                    int UID = -1;
                    String title = "";
                    String Description = "";
                    int ResType = 0;
                    for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){
                        if(unique.getKey() == "Name"){
                            name = unique.getValue(String.class);
                        }else if(unique.getKey() == "UID"){
                            UID = unique.getValue(int.class);
                        }else if(unique.getKey() == "Title"){
                            title = unique.getValue(String.class);
                        }else if(unique.getKey() == "Description"){
                            Description = unique.getValue(String.class);
                        }else if(unique.getKey() == "ResType"){
                            ResType = unique.getValue(int.class);
                        }
                    }
                    qu.add(new Resource(name, UID, title, Description, ResType));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
            }

        });

        return qu;}


    public Vector<ArticleResource> pullArticleResources(){
        final Vector<ArticleResource> qu = new Vector<ArticleResource>();
        ref.child("Resource").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    int UID = -1;
                    String url = "";
                    for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){
                        if(unique.getKey() == "UID"){
                            UID = unique.getValue(int.class);
                        }else if(unique.getKey() == "URL"){
                            url = unique.getValue(String.class);
                        }
                    }
                    qu.add(new ArticleResource(UID,url));
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
            }

        });

        return qu;}



}
