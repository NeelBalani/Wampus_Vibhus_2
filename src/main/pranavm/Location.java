package edu.bothell.wampus.pranavm;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private List<Hazard> hazards;

    public Location() {
        this.hazards = new ArrayList<>();
    }

    public void addHazard(Hazard h) {
        this.hazards.add(h);
    }

    public String getWarnings() {
        StringBuilder allWarnings = new StringBuilder();
        for (Hazard hazard : this.hazards) {
            allWarnings.append(hazard.getWarning()).append("\n");
        }
    
        if (allWarnings.length() > 0) {
            allWarnings.deleteCharAt(allWarnings.length() - 1);
        }
        return allWarnings.toString();
    }
}