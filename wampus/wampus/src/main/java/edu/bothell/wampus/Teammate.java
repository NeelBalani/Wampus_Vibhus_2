package edu.bothell.wampus;

import java.util.List;

public interface Teammate {
    List<String> getActions();
    Result doAction(UI ui);
}
