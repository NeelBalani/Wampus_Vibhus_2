package edu.bothell.wampus.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriviaManager {
    private List<String> questionArray = new ArrayList<>();
    private List<String> correctAnswerArray = new ArrayList<>();
    private List<String[]> possibleAnswerArray = new ArrayList<>();

    public TriviaManager(File filename) {
        System.out.println("TriviaManager created with file: " + filename.getAbsolutePath());
        loadQuestionsFromFile(filename);
    }

    private void loadQuestionsFromFile(File filename) {
        System.out.println("Loading questions from file...");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                System.out.println("Read line " + lineNumber + ": " + line);
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    System.out.println("Line " + lineNumber + " has enough parts.");
                    questionArray.add(parts[0].replace("\"", ""));
                    correctAnswerArray.add(parts[1].replace("\"", ""));
                    String[] possibleAnswers = new String[4];
                    for (int i = 2; i < 6; i++) {
                        possibleAnswers[i - 2] = parts[i].replace("\"", "");
                        System.out.println("  Possible answer " + (i - 2) + ": " + possibleAnswers[i - 2]);
                    }
                    possibleAnswerArray.add(possibleAnswers);
                    System.out.println("  Question added: " + parts[0]);
                    System.out.println("  Total questions now: " + questionArray.size());
                } else {
                    System.out.println("Warning: Line " + lineNumber + " does not have at least 6 parts. Skipping.");
                }
            }
            System.out.println("Finished loading questions. Total questions loaded: " + questionArray.size());
        } catch (IOException e) {
            System.err.println("Error loading questions from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getQuestion(int num) {
        if (num >= 0 && num < questionArray.size()) {
            return questionArray.get(num);
        }
        return null; 
    }

    public String getCorrectAnswer(int num) {
        if (num >= 0 && num < correctAnswerArray.size()) {
            return correctAnswerArray.get(num);
        }
        return null;
    }

    public String[] getPossibleAnswers(int num) {
        if (num >= 0 && num < possibleAnswerArray.size()) {
            return possibleAnswerArray.get(num);
        }
        return null; 
    }

    public int getTotalQuestions() {
        return questionArray.size();
    }
}