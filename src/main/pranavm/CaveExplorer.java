package edu.bothell.wampus.pranavm;

public class CaveExplorer {
    private Location[][] cave;
    private int rows;
    private int cols;

    public CaveExplorer(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cave = new Location[rows][cols];
    }

    public void addHazardAt(int row, int col, Hazard h) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            if (cave[row][col] == null) {
                cave[row][col] = new Location();
            }
            cave[row][col].addHazard(h);
        } else {
            System.out.println("Invalid row or column.");
        }
    }

    public void getAllWarnings() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cave[i][j] != null && !cave[i][j].getWarnings().isEmpty()) {
                    System.out.println("At (" + i + ", " + j + "):");
                    System.out.println(cave[i][j].getWarnings());
                }
            }
        }
    }

    public static void main(String[] args) {
        CaveExplorer explorer = new CaveExplorer(5, 5);

        explorer.addHazardAt(1, 2, new Pit());
        explorer.addHazardAt(1, 2, new Wumpus());
        explorer.addHazardAt(3, 0, new Bat());

        explorer.getAllWarnings();
    }
}   