package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.data.QuestionType;

public class PercentageAnswer extends Answer implements Serializable {

    private Integer mPercent = 0;

    public PercentageAnswer(int percent) {
        mPercent = percent;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.PercentageAnswer;
    }

    @Override
    public Integer getValue() {
        return mPercent;
    }

    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        return new PercentageAnswer(obj.getInt("value"));
    }

    @Override
    public String toString(){
        return ("Percentage: " + mPercent.toString());
    }
}
