package haverford.therapy_assistant.data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class Exercise implements Serializable {
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

    public String toString(){
        return "{\n\t\"uID\": "+mUID+",\n\t\"name\": "+mName+",\n\t\"questions\": "+mQuestions.toString()+"\n}";
    }
}
