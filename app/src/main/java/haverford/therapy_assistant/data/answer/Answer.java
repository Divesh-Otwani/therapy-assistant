package haverford.therapy_assistant.data.answer;

import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;

public abstract class Answer {
    public abstract QuestionType getQuestionType();
    public abstract JSONObject toJSON();
    public abstract Answer fromJSON(JSONObject obj);
}
