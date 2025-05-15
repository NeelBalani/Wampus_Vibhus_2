package edu.bothell.wampus;

public class AnswerSubmission {

    private int questionId;
    private int selected;

    public AnswerSubmission(int questionId, int selected){
        questionId = this.questionId;
        selected = this.selected;
    }

    public int getQuestionId(){
        return questionId;
    }

    public int getSelected(){
        return selected;
    }
}
