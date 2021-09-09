package ca.ubc.cs304.model;

abstract public class Consumable {
    private final String name;
    private final double caloriePer100GramOrMl;

    public Consumable(String name, double caloriePer100GramOrMl) {
        this.name = name;
        this.caloriePer100GramOrMl = caloriePer100GramOrMl;
    }

    public double getCaloriePer100GramOrMl() {
        return caloriePer100GramOrMl;
    }

    public String getName() {
        return name;
    }
}
