package haverford.therapy_assistant.data.answer;

import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;

public class TextAnswer extends Answer {

    @Override
    public QuestionType getQuestionType() {
        return null;
    }

    @Override
    public JSONObject getValue() {
        return null;
    }
}
