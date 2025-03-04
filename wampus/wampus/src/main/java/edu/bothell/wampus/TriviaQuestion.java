package edu.bothell.wampus;
import java.util.Random;

public class TriviaQuestion {
    // Properties
    private String answer;
    private String question;
    private TriviaManager tmanager;

    // Constructors
    public TriviaQuestion(TriviaManager tmanager){
        Random r =  new Random();
        getQuestionFromTM(r.nextInt(50));
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
        question = tmanager.getQuestion(rand);
        answer = tmanager.getAnswer(rand);
    }
}
