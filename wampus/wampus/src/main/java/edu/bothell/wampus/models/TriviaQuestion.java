package edu.bothell.wampus.models;

import edu.bothell.wampus.controllers.TriviaManager;

import java.util.Random;

public class TriviaQuestion {
    // Properties
    private String answer;
    private String question;
    private String[] possibleAnswers;
    private TriviaManager tmanager;

    // Constructor
    public TriviaQuestion(String question, String correctAnswer, String[] possibleAnswers) {
        this.question = question;
        this.answer = correctAnswer;
        this.possibleAnswers = possibleAnswers;
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

    public String[] getPossibleAnswers() {
        return this.possibleAnswers;
    }

}
