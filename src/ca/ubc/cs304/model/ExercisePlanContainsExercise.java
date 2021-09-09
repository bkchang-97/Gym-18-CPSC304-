package ca.ubc.cs304.model;

public class ExercisePlanContainsExercise {
    private final String name;
    private final int planId;

    public ExercisePlanContainsExercise(String name, int planId) {
        this.name = name;
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public int getPlanId() {
        return planId;
    }
}
