package ca.ubc.cs304.model;

public class UserPracticesExercisePlan {
    private final int planId;
    private final int userId;

    public UserPracticesExercisePlan(int planId, int userId) {
        this.planId = planId;
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public int getUserId() {
        return userId;
    }
}
