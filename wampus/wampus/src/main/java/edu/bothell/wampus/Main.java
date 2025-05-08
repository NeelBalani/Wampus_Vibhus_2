package edu.bothell.wampus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Initialize controller
        System.out.println("starting");
        Controller controller = new Controller("Wampus_Vibhus_2/wampus/wampus/src/main/java/edu/bothell/wampus/maps/MapGraph.csv");
        controller.addPerson(new Pranav("Pranav"));

        UI ui = new ConsoleUI(controller);

        controller.start();
        

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        
        //QuestionInitializer questionInitializer = new QuestionInitializer();
        //TriviaManager triviaManager = questionInitializer.fromFile("trivia.csv");
        
        /*
        for (int i = 0; i < triviaManager.getTotalQuestions(); i++) {
            System.out.println("Question " + (i + 1) + ": " + triviaManager.getQuestion(i));
            System.out.println("Correct Answer: " + triviaManager.getCorrectAnswer(i));
            System.out.print("Possible Answers: ");
            for (String answer : triviaManager.getPossibleAnswers(i)) {
                System.out.print(answer + " ");
            }
            System.out.println("\n");
        }
        */
        
        System.out.println("is it testing");

        //controller.start();
    }
}
