package edu.bothell.wampus;
import java.util.Random;

public class TriviaQuestion {
    // Properties
    private String answer;
    private String question;

    // Constructors
    public TriviaQuestion(){
        Random r =  new Random();
    }

    // Methods

    public boolean checkAnswer(String response){
        return response.equalsIgnoreCase(this.answer);
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public void getQuestionFromTM(int rand){
        
    }
}
