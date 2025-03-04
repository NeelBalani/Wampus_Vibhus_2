package edu.bothell.wampus;

public class TriviaManager {

    private String[] questionArray = new String[]{
    };

    private String[] answerArray = new String[]{};

    public TriviaManager(){

    }

    public String getQuestion(int num){
        return questionArray[num];
    }

    public String getAnswer(int num){
        return answerArray[num];
    }
    
}
