package haverford.therapy_assistant.localstore;

// Brian should work primarily in this package.

import android.content.Context;
import android.util.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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


    // Implement this too
    public HashMap<Date,Vector<Exercise>> queryExercises(){
        return null;
    }

}
