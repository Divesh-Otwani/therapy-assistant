package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;

public abstract class Answer {

    public abstract QuestionType getQuestionType();
    public JSONObject toJSON() throws  JSONException{
        String jsonStart = "{\n\t\"value\": ";
        String jsonEnd = "\n}";

        String json = jsonStart+ "\""+getValue()+"\""+jsonEnd;

            return new JSONObject(json);
    }

    public abstract Answer fromJSON(JSONObject obj) throws JSONException;

    public abstract Object getValue();
}
