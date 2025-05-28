package edu.bothell.wampus;

public class HighScore {

    private boolean survive;
    private int totalTurns;
    private int totalGold;
    private int totalArrows;
    private double score = 0.0d;

    public HighScore(boolean survive,int turns, int gold, int arrows){
        totalTurns = turns;
        totalGold = gold;
        totalArrows = arrows;
        this.survive = survive;
        setScore();
    }

    public void setScore() {
        if(survive) {
            score += 100;
            score -= (double) totalTurns * 0.1;
            score -= totalArrows;
            score += (double) totalGold * 0.5;
        }
    }

    public double getScore(){
        return score;
    }

}
