package haverford.therapy_assistant.data;

import java.util.Optional;

public class Question {
    private int mUID;
    private QuestionType mQType;
    private Optional<Answer> mAnswer;
    private String question;
    public Question(int uid, int qtype){
        mUID = uid;
        try{
        if(0 <= qtype && qtype <= 2){
            mQType = QuestionType.values()[qtype];
        }}catch (Exception e){
            throw e;
        }

    }

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
