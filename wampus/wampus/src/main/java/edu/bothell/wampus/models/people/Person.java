package edu.bothell.wampus.models.people;

import edu.bothell.wampus.models.Result;
import edu.bothell.wampus.interfaces.UI;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private int ammo = 3;
    private int gold = 0;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getGold() {
        return gold;
    }

    public void shoot() {
        this.ammo -= 1;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void removeGold(int amount) {
        this.gold -= amount;
    }
}
