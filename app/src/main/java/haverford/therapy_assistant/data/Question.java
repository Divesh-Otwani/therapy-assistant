package haverford.therapy_assistant.data;

import org.json.JSONObject;

import java.util.Optional;

import haverford.therapy_assistant.data.answer.Answer;


public class Question {
    private int mUID;
    private QuestionType mQType;
    private Optional<Answer> mAnswer;
    private String mPrompt;
    private String mName;

    public Question(int uid, QuestionType qtype, String prompt, String name){
        mUID = uid;
        mQType = qtype;
	    mPrompt = prompt;
	    mName = name;
    }


    /**
     *
     * @param ans
     * @return True if answer is the right type.
     */
    public boolean answerQuestion(Answer ans){
        return false;
    }

    public boolean isAnswered(){return mAnswer.isPresent();}


    /**
     * Returns real value of answer. Only call after checking isAnswered.
     * @return
     */
    public JSONObject getAnswer(){
        if(isAnswered()) return mAnswer.get().getValue();
        else return null;
    }

    public int getUID(){return mUID;}
    public QuestionType getQType(){return mQType;}
    public String getPrompt(){return mPrompt;}
    public String getName(){return mName;}
}
