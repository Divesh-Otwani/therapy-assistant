package haverford.therapy_assistant.localstore;

// Brian should work primarily in this package.

import android.content.Context;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;

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
        return context.getDir(date.toString(),Context.MODE_PRIVATE);
    }

    /**
     * Gets writer to help store exercise as json data.
     * @param toWrite
     */
    private JsonWriter getJSONWriter(File toWrite)
    {
        try {
            return new JsonWriter(new FileWriter(toWrite));
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
    private void writeExercise(JsonWriter writer, Exercise e) throws IOException
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
    private void writeQuestionList(JsonWriter writer, Vector<Question> questions) throws IOException{
        writer.beginArray();
        for(Question q : questions)
            writeQuestion(writer,q);
        writer.endArray();
    }

    /**
     * Encodes a question in Json.
     * @param writer
     * @param question
     */
    private void writeQuestion(JsonWriter writer, Question question) throws IOException{
        writer.beginObject();

        writer.name("uID").value(question.getUID());
        writer.name("name").value(question.getName());
        writer.name("qType").value(question.getQType().ordinal());
        writer.name("prompt").value(question.getPrompt());
        if(!question.isAnswered()) writer.name("answer").nullValue();
        else {
            Object ans = question.getAnswer();
            switch(question.getQType())
            {
                case TextAnswer: writer.name("answer").value((String) ans);
                break;
                case PercentageAnswer: writer.name("answer").value((double) ans);
                break;
                case ScaleOfTenAnswer: writer.name("answer").value((int) ans);
                break;
                default: writer.name("answer").nullValue();
            }
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

        JsonWriter writer = getJSONWriter(storedExercise);
        writer.setIndent("  ");
        try {
            writeExercise(writer,e);
            writer.close();
        }catch(IOException ex) {
            ex.printStackTrace();
        }

        return writer!=null;
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

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String json = null;
        String next = null;

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

        String[] dirList = context.fileList();

        for(String dir : dirList) {
            try {
                Date date = Date.valueOf(dir);
                File dateDir = context.getDir(dir, Context.MODE_PRIVATE);
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
