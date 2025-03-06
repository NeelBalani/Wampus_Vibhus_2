package edu.bothell.wampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriviaManager {
    private List<String> questionArray = new ArrayList<>();
    private List<String> correctAnswerArray = new ArrayList<>();
    private List<String[]> possibleAnswerArray = new ArrayList<>();

    public TriviaManager(String filename) {
        loadQuestionsFromFile(filename);
    }

    private void loadQuestionsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    questionArray.add(parts[0].replace("\"", ""));
                    correctAnswerArray.add(parts[1].replace("\"", ""));
                    String[] possibleAnswers = new String[4];
                    for (int i = 2; i < 6; i++) {
                        possibleAnswers[i - 2] = parts[i].replace("\"", "");
                    }
                    possibleAnswerArray.add(possibleAnswers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getQuestion(int num) {
        return questionArray.get(num);
    }

    public String getCorrectAnswer(int num) {
        return correctAnswerArray.get(num);
    }

    public String[] getPossibleAnswers(int num) {
        return possibleAnswerArray.get(num);
    }

    public int getTotalQuestions() {
        return questionArray.size();
    }
}
