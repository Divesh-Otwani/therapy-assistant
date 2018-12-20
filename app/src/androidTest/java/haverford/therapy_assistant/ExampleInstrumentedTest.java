package haverford.therapy_assistant;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
import haverford.therapy_assistant.data.answer.MultipleChoiceAnswer;
import haverford.therapy_assistant.data.answer.TextAnswer;
import haverford.therapy_assistant.localstore.LocalStorage;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("haverford.therapy_assistant", appContext.getPackageName());
    }



    @Test
    public void test_Storage() {
        Vector<Question> inQ =  new Vector<Question>();

        Question first = new Question(8,QuestionType.TextAnswer,"Is this a test?", "Test Question");
        first.answerQuestion(new TextAnswer("YESSSS"));
        Question second = new Question(9,QuestionType.MultipleChoiceAnswer, "DO THE MATH","MultQuest");

        String[] prompts = {"1+1","8+8","ok im done"};
        String response = "8+8";

        second.answerQuestion(new MultipleChoiceAnswer(prompts).setResponse(response));

        inQ.add(first);
        inQ.add(second);

        Exercise inTest = new Exercise(88,"Test Exercise",inQ);

        Log.d("Bloop","INITIAL:\n"+inTest.toString());

        LocalStorage store = new LocalStorage(InstrumentationRegistry.getTargetContext());

        Date now = new Date();

        store.storeExercise(now,inTest);

        Exercise outTest = store.queryExercises().get(new Date(now.getTime())).get(0);
        Log.d("Blarp","AFTER:\n"+outTest.toString());

        assertTrue(outTest.getName().equals("Test Exercise"));
    }
}
