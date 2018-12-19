package haverford.therapy_assistant.localstore;

// Brian should work primarily in this package.

import android.content.Context;
import android.database.DataSetObserver;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.Answer;
import haverford.therapy_assistant.data.answer.MultipleChoiceAnswer;
import haverford.therapy_assistant.data.answer.PercentageAnswer;
import haverford.therapy_assistant.data.answer.ScaleOfTenAnswer;
import haverford.therapy_assistant.data.answer.TextAnswer;

public class LocalStorage {

    private Context context;

    public LocalStorage(Context c){
        context = c;
    }

    /**
     *  Makes or gets existing directory with the name of the string
     *  the date object represents.
     * @param date
     * @return Returns File representing the directory
     */
    private File getDir(Date date) {
        return context.getDir(date.toString(),Context.MODE_APPEND);
    }

    /**
     * Gets writer to help store exercise as json data.
     * @param toWrite
     */
    private JsonWriter getJSONWriter(File toWrite)
    {
        try {
            //return new JsonWriter(new FileWriter(toWrite));
            return new JsonWriter(new OutputStreamWriter(new FileOutputStream(toWrite),"UTF-8"));
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private FileWriter getFileWriter(File toWrite)
    {
        try {
            return new FileWriter(toWrite);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  Encodes the exercise in Json.
     * @param writer
     * @param e
     */
    private void writeExercise(JsonWriter writer, Exercise e) throws IOException, JSONException
    {
        writer.beginObject();
        writer.name("uID").value(e.getUID());
        writer.name("name").value(e.getName());
        if(e.getQuestions()==null) writer.name("questions").nullValue();
        else {
            writer.name("questions");
            writeQuestionList(writer,e.getQuestions());
        }
        writer.endObject();

    }

    /**
     * Encodes a question list in Json.
     * @param writer
     * @param questions
     */
    private void writeQuestionList(JsonWriter writer, Vector<Question> questions) throws IOException,JSONException{
        writer.beginArray();
        for(Question q : questions)
            writeQuestion(writer,q);
        writer.endArray();

    }

    /**
     * Encodes a question list in Json.
     * @param writer
     * @param answers
     */
    private void writeJSONArray(JsonWriter writer, JSONArray answers) throws IOException, JSONException{
        writer.beginArray();
        for(int i =0; i<answers.length(); i++) {
            boolean valB = answers.optBoolean(i);
            double valD = answers.optDouble(i,-11);
            String valS = answers.optString(i,null);
            JSONArray valJA = answers.optJSONArray(i);
            JSONObject valJO = answers.optJSONObject(i);

            if(valJO!=null) {writeAnswer(writer,valJO);}
            else if (valJA!=null) {writeJSONArray(writer,valJA); }
            else if (valS!=null) {writer.value(valS); }
            else if (valD!=-11) {writer.value(valD); }
            else if (answers.isNull(i)){writer.nullValue();}
            else writer.value(valB);
        }
        writer.endArray();
    }

    /**
     * Encodes a question in Json.
     * @param writer
     * @param question
     */
    private void writeQuestion(JsonWriter writer, Question question) throws IOException,JSONException{
        writer.beginObject();

        writer.name("uID").value(question.getUID());
        writer.name("name").value(question.getName());
        writer.name("qType").value(question.getQType().ordinal());
        writer.name("prompt").value(question.getPrompt());
        if(!question.isAnswered()) writer.name("answer").nullValue();
        else {
            writer.name("answer");
            writeAnswer(writer,question.getAnswer());

        }

        writer.endObject();

    }

    /**
     * Encodes answer in json.
     * @param writer
     * @param answer
     */
    private void writeAnswer(JsonWriter writer, JSONObject answer) throws IOException, JSONException{
        writer.beginObject();

        Iterator<String> iterator = answer.keys();

        while(iterator.hasNext()) {
            String next = iterator.next();
            boolean valB = answer.optBoolean(next);
            double valD = answer.optDouble(next,-11);
            String valS = answer.optString(next,null);
            JSONArray valJA = answer.optJSONArray(next);
            JSONObject valJO = answer.optJSONObject(next);

            if(valJO!=null) {writer.name(next);writeAnswer(writer,valJO);}
            else if (valJA!=null) {writer.name(next);writeJSONArray(writer,valJA); }
            else if (valS!=null) {writer.name(next).value(valS); }
            else if (valD!=-11) {writer.name(next).value(valD); }
            else if (answer.isNull(next)){writer.name(next).nullValue();}
            else writer.name(next).value(valB);
        }

        writer.endObject();
    }

    /**
     * Stores an exercise (even if incomplete).
     * @param e
     * @return Returns true if stored successfully.
     */
    public boolean storeExercise(Date date, Exercise e){

        File targetDir = getDir(date);
        String exerciseFilename = "exercise-"+date.toString()+"-"+System.nanoTime()+".json";
        File storedExercise = new File(targetDir,exerciseFilename);

        //storedExercise.createNewFile();

        JsonWriter writer = getJSONWriter(storedExercise);
        writer.setIndent("  ");
        try {
            writeExercise(writer,e);
            writer.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        double len = storedExercise.length();
        return writer!=null;

    }

    /**
     * Returns answer object from question.
     * TODO: implement
     * @param answer
     * @return
     */
    private Answer interpretAnswer(QuestionType qType, JSONObject answer) throws  JSONException {

        switch (qType)
        {
            case TextAnswer: return new TextAnswer(null).fromJSON(answer);
            case PercentageAnswer: return new PercentageAnswer(0).fromJSON(answer);
            case ScaleOfTenAnswer: return new ScaleOfTenAnswer(0).fromJSON(answer);
            case MultipleChoiceAnswer: return new MultipleChoiceAnswer().fromJSON(answer);
            default: return null;
        }
    }

    /**
     * Converts Jsonobject to question data type.
     * @param question
     * @return
     */
    private Question interpretQuestion(JSONObject question) throws  JSONException{
        Question out = new Question(question.getInt("uID"),
                QuestionType.values()[question.getInt("qType")],
                                    question.getString("prompt"),
                                    question.getString("name"));
        if(!question.isNull("answer")) {
            out.answerQuestion(interpretAnswer(QuestionType.values()[question.getInt("qType")], question.getJSONObject("answer")));
        }

        return out;
    }

    /**
     * Interprets json array as vector of questions.
     * @param questions
     * @return The vector.
     */
    private Vector<Question> interpretQuestions(JSONArray questions) throws JSONException {
        Vector<Question> out = new Vector<Question>();

        for(int i = 0; i<questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            Question q = interpretQuestion(question);
            out.add(q);
        }

        return out;
    }

    /**
     * Transforms JSON string into exercise.
     * @param json
     * @return The interpreted exercise.
     */
    private Exercise interpretJSONEx(String json) {

        try {
            JSONObject exercise = new JSONObject(json);
            Exercise out = new Exercise(exercise.getInt("uID"),
                                        exercise.getString("name"),
                                        interpretQuestions(exercise.getJSONArray("questions")));
            return out;

        } catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates exercise from json file.
     * @param file
     * @return Returns Vector of exercises
     */
    private Exercise generateExercise(File file) throws IOException {

        double len = file.length();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String json = null;
        String next = null;
        //next = reader.readLine();

        while((next = reader.readLine())!=null) {
            if(json==null) json = next+"\n";
            else json+=next+"\n";
        }

        reader.close();

        Exercise out = interpretJSONEx(json);

        return out;
    }

    /**
     * Generates Vector of exercises from json files.
     * @param files
     * @return Returns Vector of exercises
     */
    private Vector<Exercise> generateExercises(File[] files) throws IOException {
        Vector<Exercise> out = new Vector<Exercise>();

        for(File file : files)
        {
            Exercise in = generateExercise(file);
            if(in!=null) out.add(in);
        }

        return out;
    }

    /**
     * Returns all saved exercises. Sorts by date.
     * @return
     */
    public HashMap<Date,Vector<Exercise>> queryExercises(){
        HashMap<Date, Vector<Exercise>> out = new HashMap<Date, Vector<Exercise>>();

        String[] dirList = context.getDataDir().list();

        for(String dir : dirList) {
            try {
                String dateGet = dir.substring(4);
                Date date = Date.valueOf(dateGet);
                File dateDir = context.getDir(dateGet, Context.MODE_APPEND);
                if(dateDir.isDirectory()) {
                    File[] exercises = dateDir.listFiles();
                    Vector<Exercise> exer = generateExercises(exercises);
                    if(!exer.isEmpty())out.put(date,exer);
                }
            } catch(IllegalArgumentException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return out;
    }

}
