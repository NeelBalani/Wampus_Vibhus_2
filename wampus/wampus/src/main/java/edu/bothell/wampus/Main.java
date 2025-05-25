package edu.bothell.wampus;

import edu.bothell.wampus.controllers.GameController;
import edu.bothell.wampus.controllers.TriviaManager;
import edu.bothell.wampus.initializers.QuestionInitializer;
import edu.bothell.wampus.models.people.Pranav;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class Main {
    //TODO:
        //StartScreen
        //Questions
        //print notice on gui
        //add functions to Hazards
        //Make Wampus move
        //Make Wampus & player attack
        //make attack heal buttons
        //finish the whole point system
            //gold
                //trivia
            //number of trivia
            //number of moves
    public static void main(String[] args) throws FileNotFoundException {

        // Initialize controller
        System.out.println("starting");
        GameController controller = new GameController("wampus/wampus/src/main/resources/edu/bothell/wampus/maps/MapGraph.csv");
        controller.addPerson(new Pranav("Pranav"));

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        QuestionInitializer initializer = new QuestionInitializer();
        File triviaFile = new File("trivia.csv");
        TriviaManager triviaManager = initializer.fromFile(triviaFile);

        if (triviaManager.getTotalQuestions() > 0) {
            Scanner scanner = new Scanner(System.in);

            int totalQuestions = triviaManager.getTotalQuestions();
            int score = 0;

            System.out.println("\n--- TRIVIA IS STARTING NOW MWAHAHAHA KYS! ---");

            for (int i = 0; i < totalQuestions; i++) {
                String questionText = triviaManager.getQuestion(i);
                String correctAnswer = triviaManager.getCorrectAnswer(i);
                String[] possibleAnswers = triviaManager.getPossibleAnswers(i);

                System.out.println("\nQuestion " + (i + 1) + " of " + totalQuestions + ":");
                System.out.println("Question: " + questionText);

                System.out.println("Possible Answers:");
                for (int j = 0; j < possibleAnswers.length; j++) {
                    if (possibleAnswers[j] != null) {
                        System.out.println((char)('A' + j) + ") " + possibleAnswers[j]);
                    } else {
                        System.out.println((char)('A' + j) + ") [Answer not available]");
                    }
                }

                String userAnswerChoice = "";
                String selectedAnswerText = null;

                while (selectedAnswerText == null) {
                    System.out.print("Enter your answer (A, B, C, or D): ");
                    userAnswerChoice = scanner.nextLine().trim();

                    if (userAnswerChoice.length() == 1) {
                        char choiceChar = Character.toUpperCase(userAnswerChoice.charAt(0));
                        int choiceIndex = choiceChar - 'A';

                        if (choiceIndex >= 0 && choiceIndex < possibleAnswers.length) {
                            selectedAnswerText = possibleAnswers[choiceIndex];
                        } else {
                            System.out.println("Invalid choice. Please enter A, B, C, or D.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a single letter (A, B, C, or D).");
                    }
                }

                if (selectedAnswerText.equalsIgnoreCase(correctAnswer)) {
                    System.out.println("Correct! Well done.");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + correctAnswer);
                }
            }

            scanner.close();
            System.out.println("\n--- TRIVIA SESSION IS OVER! ---");
            System.out.println("You answered " + score + " out of " + totalQuestions + " questions correctly.");

        } else {
            System.out.println("No trivia questions loaded. Please check your 'trivia.csv' file and its path.");
        }

        System.out.println("\nExiting application.");
    }
}