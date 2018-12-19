package haverford.therapy_assistant.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Optional;

import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.maybe.Just;
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
        if(ans==null) return false;
        mAnswer = new Just(ans);
        return mAnswer.getValue().isPresent();
    }

    public boolean isAnswered(){return mAnswer.getValue().isPresent();}


    /**
     * Returns real value of answer. Only call after checking isAnswered.
     * @return
     */

    public JSONObject getAnswer() {
        try {
            if (isAnswered()) return mAnswer.getValue().get().toJSON();
            else return null;
        }catch(JSONException e){e.printStackTrace(); return null;}

    }

    public String getAnswerString(){
        if (mAnswer instanceof Just){
            Just<Answer> casted = (Just<Answer>) mAnswer;
            Answer ans = casted.getE();
            return ans.toString();
        } else {
            return "";
        }
    }

    public int getUID(){return mUID;}
    public QuestionType getQType(){return mQType;}
    public String getPrompt(){return mPrompt;}
    public String getName(){return mName;}

    public String toString(){
        return "{\n\t\"uID\": "
                + mUID+",\n\t\"name\": "
                + mName+",\n\t\"qType\": "
                + mQType+",\n\t\"prompt\": "
                + mPrompt+",\n\t\"answer\": "
                + (mAnswer.getValue().isPresent() ? mAnswer.getValue().get().toString() : "NULL")+"\n}";
    }
}
