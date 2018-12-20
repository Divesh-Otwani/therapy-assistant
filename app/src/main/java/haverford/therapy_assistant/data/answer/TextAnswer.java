package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;

public class TextAnswer extends Answer implements Serializable {

    private String mAnswer = "";

    public TextAnswer(String ans) {
        if (ans != null) {
            mAnswer = ans;
        } else {
            mAnswer = "(No response given.)";
        }
    }


    @Override
    public QuestionType getQuestionType() {
        return QuestionType.TextAnswer;
    }

    public JSONObject getAnswer() {
        return null;
    }



    public String getValue() {return mAnswer;}

    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        return new TextAnswer(obj.getString("value"));
    }


    @Override
    public String toString(){
        return ("Response: " + mAnswer);
    }

}
