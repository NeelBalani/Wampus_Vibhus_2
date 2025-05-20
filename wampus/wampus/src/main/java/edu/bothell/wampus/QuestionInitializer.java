package edu.bothell.wampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class QuestionInitializer {
    
    public TriviaManager fromFile(File filename) {
        return new TriviaManager(filename);
    }
}
