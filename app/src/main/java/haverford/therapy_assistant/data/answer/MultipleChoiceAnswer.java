package haverford.therapy_assistant.data.answer;

import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import haverford.therapy_assistant.data.QuestionType;

public class MultipleChoiceAnswer extends Answer implements Serializable {

    private String[] mPrompts; // row,col
    private int length;
    String mResponse;

    public MultipleChoiceAnswer(String[] prompts) {
        mPrompts = prompts;
        length = mPrompts.length;
        mResponse = "No option chosen!";
    }
    public MultipleChoiceAnswer() {
    }

    @Override
    public String[] getStringArr(){
        return mPrompts;
    }

    public Answer setResponse(String response) {
        mResponse = response;
        return this;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MultipleChoiceAnswer;
    }

    @Override
    public JSONObject toJSON() throws  JSONException{
        String jsonStart = "{";
        String jsonTemplate = "\n\t\"value";
        String jsonTemp2 = "\": ";

        String json = jsonStart;


        for(int i = 0; i < length; i++) {
            json+= jsonTemplate+i+jsonTemp2+"\""+mPrompts[i]+"\",";
        }
        json+= "\n\t\"response\": \""+(mResponse==null? JSONObject.NULL : mResponse)+"\"\n}";

        return new JSONObject(json);
    }

    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        String[] prompts;
        String response;
        int i =0;
        while(obj.optString("value"+i,null)!=null) {
           i++;
        }
        prompts = new String[i];

        i =0;
        while(obj.optString("value"+i,null)!=null) {
            prompts[i] = obj.getString("value"+i);
            i++;
        }

        response = obj.getString("response");

        MultipleChoiceAnswer out = new MultipleChoiceAnswer(prompts);
        out.setResponse(response);

        return out;
    }

    @Override
    public String getValue() {
        return mResponse;
    }

    @Override
    public String toString(){
        return ("The option you chose:\n" + mResponse);
    }

}
