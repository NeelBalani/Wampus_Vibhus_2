/*package edu.bothell.wampus;

import javax.naming.directory.DirContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WallCaveInitializer {
    // Properties
    private GameLocation[][] caveBuilder;
    private int roomIdCounter = 1;
    private Cave cave;

    // Constructors
    public WallCaveInitializer(String filePath) throws FileNotFoundException {
        // Gets the file and initializes the caveBuilder ready for filling up
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        int[] caveSize = getCaveSize(file);
        this.caveBuilder = new GameLocation[caveSize[0]][caveSize[1]];

        // Fills up the caveBuilder with the map created from the file
        // Skips description
        scanner.nextLine();
        
        int rowNumber = 0;
        while(scanner.hasNextLine()){
            // Builds each row
            buildGameLocationRow(scanner.nextLine(), rowNumber);
            rowNumber++;
        }

        this.cave = new Cave(this.caveBuilder);
    }

    public Cave getBuiltCave(){
        return this.cave;
    }

    private void printCave(){
        for(int y = 0; y < caveBuilder.length; y++){

            for(int x = 0; x < caveBuilder[y].length; x++){
                System.out.print("[" + this.caveBuilder[y][x] + " " + Arrays.toString(this.caveBuilder[y][x].getLocation()));
            }
            System.out.println();
        }
    }

    private int[]getCaveSize(File file) throws FileNotFoundException {
        Scanner caveScanner = new Scanner(file);
        Scanner rowScanner;
        int rows = 0;
        int columns = 0;

        // Description
        caveScanner.nextLine();

        while(caveScanner.hasNextLine()){
            columns = 0;
            rows++;
            rowScanner = new Scanner(caveScanner.nextLine());
            while(rowScanner.hasNext()){
                if(rowScanner.next().equals(";")) columns++;
            }
        }
        return new int[]{rows, columns};
    }

    public void buildGameLocationRow(String gameRow, int rowNumber){
        Scanner scanner = new Scanner(gameRow);
        List<Directions> whereWalls;
        int columnCount = 0;


        while(scanner.hasNext()){
            whereWalls = new ArrayList<Directions>();

            for(Directions direction : Directions.values()){
                String yn = scanner.next();
                if(yn.equals("Y")) whereWalls.add(direction);
                scanner.next();
            }
            if(scanner.next().equals(";")) {
                this.caveBuilder[rowNumber][columnCount] = new GameLocation(columnCount, rowNumber, this.roomIdCounter);
                this.caveBuilder[rowNumber][columnCount].setWalls(whereWalls);
                this.roomIdCounter++;
                columnCount++;

            }
        }
    }

}
*/