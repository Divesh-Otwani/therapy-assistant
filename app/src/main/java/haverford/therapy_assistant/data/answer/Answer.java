package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.data.QuestionType;

// Note: All answers should have a default value at the start.
public abstract class Answer implements Serializable {
    private String mAnswer;
    public abstract QuestionType getQuestionType();

    public JSONObject toJSON() throws  JSONException{
        String jsonStart = "{\n\t\"value\": ";
        String jsonEnd = "\n}";

        String json = (getValue() instanceof String) ? jsonStart+ "\""+getValue()+"\""+jsonEnd : jsonStart+getValue()+jsonEnd;

            return new JSONObject(json);
    }

    public abstract Answer fromJSON(JSONObject obj) throws JSONException;

    public abstract Object getValue();

    public abstract String toString();

}
