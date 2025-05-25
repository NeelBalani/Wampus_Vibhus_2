package edu.bothell.wampus.initializers;

import edu.bothell.wampus.controllers.TriviaManager;

import java.io.File;

public class QuestionInitializer {
    
    public TriviaManager fromFile(File filename) {
        return new TriviaManager(filename);
    }
}
