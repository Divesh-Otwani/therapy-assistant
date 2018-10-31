package haverford.therapy_assistant.data;

import java.util.Optional;

public class Question {
    private int mUID;
    private QuestionType mQType;
    private Optional<Answer> mAnswer;

    /**
     *
     * @param ans
     * @return True if answer is the right type.
     */
    boolean answerQuestion(Answer ans){
        return false;
    }

    boolean isAnswered(){return mAnswer.isPresent();}
}
