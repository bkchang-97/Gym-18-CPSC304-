package ca.ubc.cs304.model;

public class DietPlan {
    private final int planId;
    private final String name;

    public DietPlan(int id, String name) {
        this.planId = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPlanId() {
        return planId;
    }
}
