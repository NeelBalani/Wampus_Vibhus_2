package edu.bothell.wampus;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdjacentCaveInitializer {
    // Properties
    private AdjacentGameLocation[] oneDimensionCave;
    private AdjacentGameLocation[] caveBuilder;
    private AdjacentCave cave;

    // Constructors
    public AdjacentCaveInitializer(String filepath) throws FileNotFoundException{
        System.out.println("cave start");
        // Gets the file and initializes the caveBuilder ready for filling up
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        int caveSize = getCaveSize(file);
        this.caveBuilder = new AdjacentGameLocation[caveSize];

        // Fills up the caveBuilder with the map created from the file
        // Skips description
        System.out.println(scanner.nextLine());

        while(scanner.hasNextLine()){
            // Builds each row
            buildGameLocationRow(scanner.nextLine());
        }

        this.cave = new AdjacentCave(this.caveBuilder);

    }

    public AdjacentCaveInitializer(InputStream caveStream) throws FileNotFoundException{
        System.out.println("cave start");


        // Gets the file and initializes the caveBuilder ready for filling up

        // Put the lines from the file into a string list
        ArrayList<String> caveLines = new BufferedReader(new InputStreamReader(caveStream))
                .lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));

        // Skips description
        caveLines.removeFirst();

        // Cave size
        int caveSize = caveLines.size();

        // Creates the caveBuilder
        this.caveBuilder = new AdjacentGameLocation[caveSize];

        // Fills up the caveBuilder with the map created from the file

        // Builds each row
        for(String row : caveLines){
            buildGameLocationRow(row);
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
            rowScanner.nextLine();
        }

        return caveSizeCounter;

    }

    public int getCaveSize(InputStream caveStream) throws FileNotFoundException {
        int caveSizeCounter = 0;
        Scanner rowScanner = new Scanner(caveStream);

        while (rowScanner.hasNextLine()) {
            caveSizeCounter++;
            rowScanner.nextLine();
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
