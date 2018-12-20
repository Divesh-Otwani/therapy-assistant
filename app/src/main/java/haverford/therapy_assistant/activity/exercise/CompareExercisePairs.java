package haverford.therapy_assistant.activity.exercise;

import java.sql.Date;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;


public class CompareExercisePairs implements Comparator<Exercise> {

    @Override
    public int compare(Exercise o1, Exercise o2) {
        long o1time = o1.getDate().getTime();
        long o2time = o2.getDate().getTime();
        if (o1time < o2time) {
            return 1;
        } else if (o1time == o2time) {
            return 0;
        } else {
            return -1;
        }
    }

}
