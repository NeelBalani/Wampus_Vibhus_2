package edu.bothell.wampus.initializers;

import edu.bothell.wampus.models.TriviaQuestion;
import edu.bothell.wampus.controllers.TriviaManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionInitializer {

    private static final String TRIVIA_CSV_PATH = "edu/bothell/wampus/trivia/trivia.csv";

    public TriviaManager initializeTriviaManager() {
        List<TriviaQuestion> questions = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TRIVIA_CSV_PATH)) {
            if (inputStream == null) {
                System.err.println("Error: Could not find trivia.csv in resources.");
                return new TriviaManager(questions);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String questionText = parts[0].replace("\"", "");
                        String correctAnswer = parts[1].replace("\"", "");
                        String[] possibleAnswers = new String[4];
                        for (int i = 0; i < 4; i++) {
                            possibleAnswers[i] = parts[i + 2].replace("\"", "");
                        }

                        TriviaQuestion tq = new TriviaQuestion(questionText, correctAnswer, possibleAnswers);
                        questions.add(tq);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read trivia.csv: " + e.getMessage());
            e.printStackTrace();
        }

        return new TriviaManager(questions);
    }
}
