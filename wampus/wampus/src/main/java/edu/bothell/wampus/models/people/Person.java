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

    public Person(String name, int ammo, int gold) {
        this.name = name;
        this.ammo = ammo;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) { this.ammo = ammo; }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) { this.gold = gold; }

    public void shoot() {
        this.ammo -= 1;
    }

    public boolean hasAmmo() { return this.ammo > 0; }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void removeGold(int amount) {
        this.gold -= amount;
    }
    
    public void subtractGold(int amount) {
        this.gold -= amount;
    }
    
    public void addArrow() {
        this.ammo += 1;
    }
    
    public void heal(int amount) {
        // Health system not implemented yet, but method stub for future use
    }
}
