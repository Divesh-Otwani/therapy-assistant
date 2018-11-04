package haverford.therapy_assistant.data.answer;

import haverford.therapy_assistant.data.QuestionType;

public abstract class Answer {
    public abstract QuestionType getQuestionType();
    public abstract Object getValue();
}
