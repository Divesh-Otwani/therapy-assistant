package haverford.therapy_assistant;

//import android.content.Context;
//import android.support.test.InstrumentationRegistry;

import org.junit.Test;


import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_Dates() {
        Date d = new java.util.Date();

        Date q = new Date(d.getTime());

        assert(d.toString().equals(q.toString()));


    }

}