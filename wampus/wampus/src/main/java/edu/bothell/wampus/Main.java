package edu.bothell.wampus;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UI ui = new ConsoleUI(controller);
        controller.addPerson(new Pranav("Pranav"));
        controller.addPlayersToLocationManager();
        new GUI(controller);
        controller.setNewPlayers();
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        
        QuestionInitializer questionInitializer = new QuestionInitializer();
        TriviaManager triviaManager = questionInitializer.fromFile("trivia.csv");
        
        for (int i = 0; i < triviaManager.getTotalQuestions(); i++) {
            System.out.println("Question " + (i + 1) + ": " + triviaManager.getQuestion(i));
            System.out.println("Correct Answer: " + triviaManager.getCorrectAnswer(i));
            System.out.print("Possible Answers: ");
            for (String answer : triviaManager.getPossibleAnswers(i)) {
                System.out.print(answer + " ");
            }
            System.out.println("\n");
        }

        
        System.out.println("is it testing");
        // controller.start();
    }
}
