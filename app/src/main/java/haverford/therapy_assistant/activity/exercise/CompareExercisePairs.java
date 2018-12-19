package haverford.therapy_assistant.activity.exercise;

import java.sql.Date;
import java.util.Comparator;
import java.util.Map;
import java.util.Vector;

import haverford.therapy_assistant.data.Exercise;


public class CompareExercisePairs implements Comparator<Exercise> {


    @Override
    public int compare(Exercise o1, Exercise o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
