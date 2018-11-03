package haverford.therapy_assistant.data;

import java.util.Optional;
import java.util.Vector;

public class Exercise {
    private int mUID;
    private String mName;
    private Vector<Question> mQuestions;
    private boolean mComplete = false;

    public boolean isComplete(){return mComplete;}

    public int getUID(){return mUID;}
    public String getName(){return mName;}
    public Vector<Question> getQuestions() {
        return mQuestions;
    }
}
