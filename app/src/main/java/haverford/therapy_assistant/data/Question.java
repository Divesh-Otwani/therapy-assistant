package haverford.therapy_assistant.data;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Optional;

import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.maybe.Maybe;
import haverford.therapy_assistant.data.maybe.Nothing;


public class Question implements Serializable {
    private int mUID;
    private QuestionType mQType;
    private Maybe<Answer> mAnswer;
    private String mPrompt;
    private String mName;

    public Question(int uid, QuestionType qtype, String prompt, String name){
        mUID = uid;
        mQType = qtype;
	    mPrompt = prompt;
	    mName = name;
	    mAnswer = new Nothing();
    }


    /**
     *
     * @param ans
     * @return True if answer is the right type.
     */
    public boolean answerQuestion(Answer ans){
        return false;
    }

    public boolean isAnswered(){return mAnswer.getValue().isPresent();}


    /**
     * Returns real value of answer. Only call after checking isAnswered.
     * @return
     */
    public JSONObject getAnswer(){
        if(isAnswered()) return mAnswer.getValue().get().toJSON();
        else return null;
    }

    public int getUID(){return mUID;}
    public QuestionType getQType(){return mQType;}
    public String getPrompt(){return mPrompt;}
    public String getName(){return mName;}
}
