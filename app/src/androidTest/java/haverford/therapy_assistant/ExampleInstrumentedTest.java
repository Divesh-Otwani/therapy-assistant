package haverford.therapy_assistant;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;
import haverford.therapy_assistant.data.Question;
import haverford.therapy_assistant.data.QuestionType;
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
        inQ.add(new Question(8,QuestionType.TextAnswer,"Is this a test?", "Test Question"));

        Exercise inTest = new Exercise(88,"Test Exercise",inQ);

        LocalStorage store = new LocalStorage(InstrumentationRegistry.getTargetContext());

        store.storeExercise(Date.valueOf("2018-9-27"),inTest);

        Exercise outTest = store.queryExercises().get(Date.valueOf("2018-9-27")).get(0);
        assertTrue(outTest.getName().equals("Test Exercise"));
    }
}
