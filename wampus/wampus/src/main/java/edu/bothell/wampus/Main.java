package edu.bothell.wampus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Initialize controller
        Controller controller = new Controller("wampus/wampus/src/main/java/edu/bothell/wampus/maps/EasyCaveMap.csv");
        controller.addPerson(new Pranav("Pranav"));

        UI ui = new ConsoleUI(controller);


        
        new GUI(controller);

        

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

        Room[] roomArray = {
            new Room(0, 3),
            new Room(1, 8),
            new Room(2, 5),
            new Room(3, 8),
            new Room(4, 1)
        };

        ArrayList<Integer> visitedRooms = new ArrayList<Integer>();
        visitedRooms.add(2);
        visitedRooms.add(3);
        visitedRooms.add(1);

        WumpusTracker wumpusTracker = new WumpusTracker(roomArray, visitedRooms);
        System.out.println(wumpusTracker.getAverageDanger());
        System.out.println(wumpusTracker.getMostDangerousVisitedRoom());

        //controller.start();
    }
}
