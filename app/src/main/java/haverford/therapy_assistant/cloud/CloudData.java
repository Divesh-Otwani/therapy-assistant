package haverford.therapy_assistant.cloud;

// Yasmine should work primiarly in this package
// You can make constructors that take whatever inputs you choose

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.Resource;

public class CloudData {
    private static final String TAG = "ViewDatabase";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("Exercise");
    private int ii;
    public CloudData() {
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            /*for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){

                }
            }*/
            ii = 10;
            showData(dataSnapshot);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
            }
        });
    }

    public void getii(){
        Log.d(TAG, "ii:" + ii);
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
    /*public void pullExercises(){
        ref.child("Exercise").addValueEventListener(new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){

                }
            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
        }
    });
    }


    // Also implement this
    public Vector<Question> pullResources(){
        Vector<Question> qu = new Vector<Question>();
        ref.child("Question").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {

                    for(DataSnapshot unique : uniqueKeySnapshot.getChildren()){

                    }
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "WHAT??: " + String.valueOf(databaseError));
            }
        });

        return qu;}*/



}
