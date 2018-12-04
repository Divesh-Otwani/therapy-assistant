package haverford.therapy_assistant.data.answer;

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

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public Answer fromJSON(JSONObject obj) {
        return null;
    }

    public String getValue() {return mAnswer;}

}
