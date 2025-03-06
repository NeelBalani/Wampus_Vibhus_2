package edu.bothell.wampus;

public class TriviaManager {

    private String[] questionArray = new String[]{
        "Sports: Who won the first Superbowl?",
        "Entertainment: What is the highest grossing movie of all time?",
        "Geography: What is the closest country to Antarctica?",
        "History: Who was the US president during WW1?",
        "Arts: What is the most expensive art piece in the world?",
        "Science: What is the lightest element on the periodic table?"
    };

    private String[] correctAnswerArray = new String[]{
        "Green Bay Packers",
        "Avatar",
        "Chile",
        "Franklin Delano Roosevelt",
        "Salvator Mundi",
        "Hydrogen"
    };

    private String[][] possibleAnswerArray = new String[][]{
        {"Green Bay Packers", "Dallas Cowboys", "Miami Dolphins", "New England Patriots"},
        {"Avatar", "Titanic", "Star Wars: The Force Awakens", "Avengers: Endgame"},
        {"Chile", "Argentina", "Australia", "New Zealand"},
        {"Woodrow Wilson", "Franklin Delano Roosevelt", "Herbert Hoover", "Warren G. Harding"},
        {"Salvator Mundi", "The Card Players", "Nafea Faa Ipoipo", "Interchange"},
        {"Hydrogen", "Helium", "Lithium", "Beryllium"}
    };

    public TriviaManager() {
    }

    public String getQuestion(int num) {
        return questionArray[num];
    }

    public String getCorrectAnswer(int num) {
        return correctAnswerArray[num];
    }

    public String[] getPossibleAnswers(int num) {
        return possibleAnswerArray[num];
    }

    public int getTotalQuestions() {
        return questionArray.length;
    }
}
