package edu.bothell.wampus;

public class TriviaQuestion {
    // Properties
    private String answer;
    private String question;

    // Constructors
    public TriviaQuestion(String question, String answer){
        this.answer = answer;
        this.question = question;
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
}
