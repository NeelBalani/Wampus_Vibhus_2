package edu.bothell.wampus;
import java.util.Random;

public class TriviaQuestion {
    // Properties
    private String answer;
    private String question;
    private TriviaManager tmanager;

    // Constructor
    public TriviaQuestion(TriviaManager tmanager) {
        this.tmanager = tmanager; 
        Random r = new Random();
        getQuestionFromTM(r.nextInt(tmanager.getTotalQuestions()));
    }

    // Methods
    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(this.answer);
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public void getQuestionFromTM(int rand) {
        question = tmanager.getQuestion(rand);
        answer = tmanager.getCorrectAnswer(rand);
    }
}