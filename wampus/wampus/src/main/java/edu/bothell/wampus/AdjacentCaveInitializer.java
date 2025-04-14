package edu.bothell.wampus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjacentCaveInitializer {
    // Properties
    private AdjacentGameLocation[] oneDimensionCave;
    private AdjacentGameLocation[] caveBuilder;
    private AdjacentCave cave;

    // Constructors
    public AdjacentCaveInitializer(String filepath) throws FileNotFoundException{
                // Gets the file and initializes the caveBuilder ready for filling up
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int caveSize = getCaveSize(file);
        this.caveBuilder = new AdjacentGameLocation[caveSize];

        // Fills up the caveBuilder with the map created from the file
        // Skips description
        scanner.nextLine();
        
        int rowNumber = 0;
        while(scanner.hasNextLine()){
            // Builds each row
            buildGameLocationRow(scanner.nextLine(), rowNumber);
            rowNumber++;
        }

        this.cave = new AdjacentCave(this.caveBuilder);        

    }

    // Methods
    public int getCaveSize(File file) throws FileNotFoundException{
        int caveSizeCounter = 0;
        Scanner rowScanner = new Scanner(file);

        while(rowScanner.hasNextLine()){
            caveSizeCounter++;
        }

        return caveSizeCounter;

    }

    public void buildGameLocationRow(String row, int rowNumber){

    }
}
