package haverford.therapy_assistant.data.answer;

import org.json.JSONException;
import org.json.JSONObject;

import haverford.therapy_assistant.data.QuestionType;

public class MultipleChoiceAnswer extends Answer{

    private String[][] mPrompts; // row,col
    private int length;
    String[] mResponses;

    public MultipleChoiceAnswer(String[][] prompts) {
        mPrompts = prompts;
        length = mPrompts.length;
        mResponses = null;
    }

    public void setResponses(String[] responses) {
        mResponses = responses;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.MultipleChoiceAnswer;
    }

    @Override
    public JSONObject toJSON() throws  JSONException{
        String jsonStart = "{";
        String jsonTemplate = "\n\t\"value";
        String jsonTemp2 = "\": "
        String jsonEnd = "\n}";

        String json = jsonStart;

        String[] responses = getValue();

        for(int i = 0; i < length; i++) {
            json+= jsonTemplate+i+jsonTemp2+"\""+mPrompts[i][0]+": "+responses[i]+"\"";
            if(i!=length-1) json+=",";
            else json+=jsonEnd;
        }


        return new JSONObject(json);
    }

    @Override
    public Answer fromJSON(JSONObject obj) throws JSONException {
        return null;
    }

    @Override
    public String[] getValue() {
        return mResponses;
    }

}
