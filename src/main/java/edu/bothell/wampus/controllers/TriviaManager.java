package edu.bothell.wampus.controllers;

import edu.bothell.wampus.models.TriviaQuestion;
import edu.bothell.wampus.initializers.QuestionInitializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriviaManager {
    private final List<TriviaQuestion> questionBank;

    public TriviaManager(List<TriviaQuestion> questionBank) {
        this.questionBank = new ArrayList<>(questionBank);
    }

    public int getTotalQuestions() {
        return questionBank.size();
    }

    public TriviaQuestion getRandomQuestion() {
        Random random = new Random();
        int index = random.nextInt(getTotalQuestions());
        return questionBank.get(index);
    }

    public TriviaQuestion getQuestion(int index) {
        if (index >= 0 && index < questionBank.size()) {
            return questionBank.get(index);
        }
        return null;
    }

    public List<TriviaQuestion> getRandomQuestions(int count) {
        List<TriviaQuestion> copy = new ArrayList<>(this.questionBank); // Make a copy so the original isn't shuffled
        Collections.shuffle(copy); // Shuffle the copy
        return copy.subList(0, Math.min(count, copy.size())); // Return a subset
    }


}
