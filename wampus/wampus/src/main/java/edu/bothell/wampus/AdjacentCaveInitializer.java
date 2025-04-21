package edu.bothell.wampus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        
        while(scanner.hasNextLine()){
            // Builds each row
            buildGameLocationRow(scanner.nextLine());
        }

        this.cave = new AdjacentCave(this.caveBuilder);        

    }

    // Methods

    public AdjacentCave getBuiltCave(){
        return this.cave;
    }

    public int getCaveSize(File file) throws FileNotFoundException{
        int caveSizeCounter = 0;
        Scanner rowScanner = new Scanner(file);

        while(rowScanner.hasNextLine()){
            caveSizeCounter++;
        }

        return caveSizeCounter;

    }

    public void buildGameLocationRow(String row){
        Scanner gameLocationRowReader = new Scanner(row);
        int mainRoomId = Integer.parseInt(gameLocationRowReader.next());
        gameLocationRowReader.next();
        
        ArrayList<Integer> connectingRoomsId = new ArrayList<Integer>();
        while (gameLocationRowReader.hasNext()) {
            connectingRoomsId.add(Integer.parseInt(gameLocationRowReader.next()));
            gameLocationRowReader.next();
        }

        AdjacentGameLocation gameLocationBuilt = new AdjacentGameLocation(mainRoomId, connectingRoomsId);
        this.caveBuilder[mainRoomId-1] = gameLocationBuilt;
    }
}
