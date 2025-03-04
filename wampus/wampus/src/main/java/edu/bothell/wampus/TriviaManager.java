package edu.bothell.wampus;

public class TriviaManager {

    private String[] questionArray = new String[]{
    };

    public TriviaManager(){

    }

    public String getQuestion(int num){
        return questionArray[num];
    }
    
}
