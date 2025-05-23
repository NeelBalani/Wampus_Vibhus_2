package edu.bothell.wampus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Initialize controller
        System.out.println("starting");
        GameController controller = new GameController("Wampus_Vibhus_2/wampus/wampus/src/main/java/edu/bothell/wampus/maps/MapGraph.csv");
        GUI gui = new GUI(controller);
        controller.addPerson(new Pranav("Pranav"));

        //UI ui = new ConsoleUI(controller);

        // controller.start();
       // GUI gui = new GUI(controller);


        System.out.println("Current working directory: " + System.getProperty("user.dir"));


        QuestionInitializer initializer = new QuestionInitializer();
        // Create a File object from the string path
        File triviaFile = new File("trivia.csv");
        TriviaManager triviaManager = initializer.fromFile(triviaFile);

        if (triviaManager.getTotalQuestions() > 0) {
            // Create a new TriviaQuestion, which will automatically pick a random question
            TriviaQuestion currentQuestion = new TriviaQuestion(triviaManager);

            // Get the question and possible answers
            String questionText = currentQuestion.getQuestion();
            String[] answers = currentQuestion.getPossibleAnswers();

            // Print the question
            System.out.println("Question: " + questionText);

            // Print the possible answers
            System.out.println("Possible Answers:");
            for (int i = 0; i < answers.length; i++) {
                System.out.println((char)('A' + i) + ") " + answers[i]);
            }

            // If you want to see the correct answer for testing purposes:
            // System.out.println("\nCorrect Answer: " + currentQuestion.getAnswer());
        } else {
            System.out.println("No trivia questions loaded.");
        }

        System.out.println("is it testing");
        //controller.start();
    }
    }
}   