package haverford.therapy_assistant.data.answer;

import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;

public class ScaleOfTenAnswer extends Answer {

    private Integer mAnswer;

    public ScaleOfTenAnswer(int answer) {
        mAnswer = answer;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.ScaleOfTenAnswer;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public Answer fromJSON(JSONObject obj) {
        return null;
    }

    @Override
    public Integer getValue() {
        return mAnswer;
    }
}
