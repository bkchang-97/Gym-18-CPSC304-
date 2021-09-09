package ca.ubc.cs304.model;

public class ExerciseGoal {
    private final String name;
    private final String goal;

    public ExerciseGoal(String name, String goal) {
        this.name = name;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public String getGoal() {
        return goal;
    }
}
