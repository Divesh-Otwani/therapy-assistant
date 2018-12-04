package haverford.therapy_assistant.data.answer;

import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;

public class PercentageAnswer extends Answer {

    private Integer mPercent;

    public PercentageAnswer(int percent) {
        mPercent = percent;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.PercentageAnswer;
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
        return mPercent;
    }
}
