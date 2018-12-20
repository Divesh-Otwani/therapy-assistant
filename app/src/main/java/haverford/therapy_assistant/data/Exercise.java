package haverford.therapy_assistant.data;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class Exercise implements Serializable,Comparable {
    private Date mDate;
    private int mUID;
    private String mName;
    private Vector<Question> mQuestions;
    private boolean mComplete = false;
    public Exercise(int id, String name, Vector<Question> questions) {
        mUID = id;
        mName = name;
        mQuestions = questions;
    }

    public boolean isComplete(){return mComplete;}
    public int getUID(){return mUID;}
    public String getName(){return mName;}
    public Vector<Question> getQuestions() {
        return mQuestions;
    }

    public Date getDate() {return mDate;}
    public void setDate(Date date) {mDate = date;}
    public String toString(){
        return "{\n\t\"uID\": "+mUID+",\n\t\"name\": "+mName+",\n\t\"questions\": "+mQuestions.toString()+"\n}";
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if (o instanceof Exercise){
            Exercise otherExercise = (Exercise) o;
            long thisTime = this.getDate().getTime();
            long otherTime = otherExercise.getDate().getTime();

            if (thisTime < otherTime){
                return 1;
            } else if (thisTime == otherTime) {
                return 0;
            } else {
                return -1;
            }
        } else { return -1; }
    }




}
