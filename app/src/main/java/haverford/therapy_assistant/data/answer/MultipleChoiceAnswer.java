package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.data.QuestionType;

public class MultipleChoiceAnswer extends Answer implements Serializable {

    private String[] mPrompts; // row,col
    private int length;
    String mResponse;

    public MultipleChoiceAnswer(String[] prompts) {
        mPrompts = prompts;
        length = mPrompts.length;
        mResponse = null;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MultipleChoiceAnswer;
    }


    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        return null;
    }

    @Override
    public String getValue() {
        return mResponse;
    }

    @Override
    public String toString(){
        return ("The option you chose:\n" + mResponse);
    }

}