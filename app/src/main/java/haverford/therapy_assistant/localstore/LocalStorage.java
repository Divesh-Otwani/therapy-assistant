package haverford.therapy_assistant.localstore;

// Brian should work primarily in this package.

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;

public class LocalStorage {

    public LocalStorage(){
    }

    /**
     * Stores an exercise (even if incomplete).
     * @param e
     * @return
     */
    public boolean storeExercise(Date date, Exercise e){ return false;}


    // Implement this too
    public HashMap<Date,Vector<Exercise>> queryExercises(){
        return null;
    }

}
