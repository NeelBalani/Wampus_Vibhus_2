package edu.bothell.wampus.interfaces;

import edu.bothell.wampus.models.Result;

import java.util.List;

public interface Teammate {
    List<String> getActions();
    Result doAction(UI ui);
}
