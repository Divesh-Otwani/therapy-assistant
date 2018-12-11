package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;

public class TextAnswer extends Answer {

    private String mAnswer;

    public TextAnswer(String ans) {
        mAnswer = ans;
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
}
