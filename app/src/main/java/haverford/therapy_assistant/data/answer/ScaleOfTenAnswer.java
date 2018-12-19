package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.activity.exercise.SelectExerciseAdapter;
import haverford.therapy_assistant.data.QuestionType;

public class ScaleOfTenAnswer extends Answer implements Serializable {

    private Integer mAnswer = 5;

    public ScaleOfTenAnswer(int answer) {
        mAnswer = answer;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.ScaleOfTenAnswer;
    }

    @Override
    public Integer getValue() {
        return mAnswer;
    }

    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        return new ScaleOfTenAnswer(obj.getInt("value"));
    }

    @Override
    public String toString(){
        return ("Given a score of " + mAnswer.toString() + " out of 10.");
    }

}
