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

    public TriviaManager() {

    }

    public String getQuestion(int num) {
        return questionArray[num];
    }

    public String getCorrectAnswer(int num) {
        return correctAnswerArray[num];
    }

    public int getTotalQuestions() {
        return questionArray.length;
    }
}