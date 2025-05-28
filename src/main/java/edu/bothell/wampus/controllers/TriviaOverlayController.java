package edu.bothell.wampus.controllers;

import edu.bothell.wampus.models.TriviaQuestion;
import edu.bothell.wampus.initializers.QuestionInitializer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaOverlayController {
    @FXML private Label scoreLabel;
    @FXML private Label questionLabel;
    @FXML private VBox answersBox;
    @FXML private Button nextButton;
    @FXML private Button exitButton;

    private static final int QUESTIONS_PER_ROUND = 5;

    private GameController gameController;
    private GameScreenController gameScreenController;
    private Parent overlayNode;

    private List<TriviaQuestion> questions;
    private int currentIndex = 0;
    private int correctCount = 0;

    public void init(GameController gameController, GameScreenController screenController, Parent overlay) {
        this.gameController = gameController;
        this.gameScreenController = screenController;
        this.overlayNode = overlay;

        loadTriviaQuestions();
        showQuestion();
    }

    private void loadTriviaQuestions() {
        QuestionInitializer questionInitializer = new QuestionInitializer();
        TriviaManager triviaManager = questionInitializer.initializeTriviaManager();
        questions = triviaManager.getRandomQuestions(QUESTIONS_PER_ROUND);
    }

    private void showQuestion() {
        if (currentIndex >= questions.size()) {
            showResults();
            return;
        }

        TriviaQuestion currentQuestion = questions.get(currentIndex);
        updateUIForQuestion(currentQuestion);
    }

    private void updateUIForQuestion(TriviaQuestion question) {
        scoreLabel.setText("Score: " + correctCount + " / " + QUESTIONS_PER_ROUND);
        questionLabel.setText(question.getQuestion());
        answersBox.getChildren().clear();
        nextButton.setVisible(false);
        exitButton.setVisible(false);

        for (String ans : question.getPossibleAnswers()) {
            Button btn = new Button(ans);
            btn.getStyleClass().add("trivia-answer-button");
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> handleAnswerSelection(btn, question));
            answersBox.getChildren().add(btn);
        }
    }

    private void handleAnswerSelection(Button selectedButton, TriviaQuestion question) {
        for (var node : answersBox.getChildren()) {
            node.setDisable(true);
        }

        boolean correct = question.checkAnswer(selectedButton.getText());
        if (correct) {
            selectedButton.getStyleClass().add("correct");
            correctCount++;
            nextButton.setVisible(true);
        } else {
            selectedButton.getStyleClass().add("incorrect");
            // Show the correct answer
            for (var node : answersBox.getChildren()) {
                Button btn = (Button) node;
                if (question.checkAnswer(btn.getText())) {
                    btn.getStyleClass().add("correct");
                    break;
                }
            }
            // End immediately on wrong answer
            showFailResults();
        }
    }

    private void showFailResults() {
        answersBox.getChildren().clear();
        questionLabel.setText("Oh no! Wrong Answer!");
        scoreLabel.setText("You scored " + correctCount + " / " + QUESTIONS_PER_ROUND);
        scoreLabel.setText(scoreLabel.getText() + "\nBetter luck next time!");
        
        exitButton.setVisible(true);
        nextButton.setVisible(false);
    }

    @FXML
    private void handleNext() {
        currentIndex++;
        showQuestion();
    }

    private void showResults() {
        answersBox.getChildren().clear();
        questionLabel.setText("Round Complete!");
        scoreLabel.setText("You scored " + correctCount + " / " + QUESTIONS_PER_ROUND);

        if (correctCount == QUESTIONS_PER_ROUND) {
            gameController.awardGold(10);
            scoreLabel.setText(scoreLabel.getText() + "\nAwarded 10 Gold!");
        }

        exitButton.setVisible(true);
        nextButton.setVisible(false);
    }

    @FXML
    private void handleExit() {
        gameScreenController.closeTriviaOverlay(overlayNode);
    }
}
