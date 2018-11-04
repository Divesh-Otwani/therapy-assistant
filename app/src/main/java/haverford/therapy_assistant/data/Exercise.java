package haverford.therapy_assistant.data;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class Exercise {
    private int mUID;
    private Vector mQuestions;
    private String mName;
    private boolean mComplete = false;
    public Exercise(int uid, List<Integer> ques, String name){
        mUID = uid;
        for(int ee : ques){
            mQuestions.add(ee);
        }
        mName = name;

    }
    public boolean isComplete(){return mComplete;}







}
