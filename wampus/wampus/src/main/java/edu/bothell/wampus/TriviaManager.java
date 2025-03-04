package edu.bothell.wampus;

public class TriviaManager {

    private String[] questionArray = new String[]{
        "Sports:",
        "Sports:",
        "Sports:",
        "Sports:",
        "Sports:",
        "Entertainment:",
        "Entertainment:",
        "Entertainment:",
        "Entertainment:",
        "Entertainment:",
        "Geography:",
        "History:",
        "Arts:",
        
    };

    public TriviaManager(){

    }

    public String getQuestion(int num){
        return questionArray[num];
    }
    
}
