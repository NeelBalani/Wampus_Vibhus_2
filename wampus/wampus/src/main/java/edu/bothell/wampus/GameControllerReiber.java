package edu.bothell.wampus;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/game")
public class GameControllerReiber {

    //this is to like combine both controllers (Game and Wampus)

    private final Game game;
    

    public GameControllerReiber(Game game) {
        this.game = game;
    }

    // Serve the game page
    @GetMapping
    public String serveGamePage(Model model) {
        model.addAttribute("title", "Wumpus Trivia");
        model.addAttribute("playerName", "Student One");
        return "index"; // maps to templates/index.html
    }

    // Serve questions as JSON
    @GetMapping("/api/questions")
    @ResponseBody
    public List getQuestions() {
        return game.getQuestions();
    }

    // Receive answer submission as JSON
    @PostMapping("/api/submit")
    @ResponseBody
    public Map<String, Object> submitAnswer(AnswerSubmission submission) {
        boolean correct;
        correct = game.checkAnswer(submission.getQuestionId(), submission.getSelected());
        return Map.of("correct", correct);
    }
}