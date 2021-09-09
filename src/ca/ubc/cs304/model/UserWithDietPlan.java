package ca.ubc.cs304.model;

public class UserWithDietPlan {
    private final String name;
    private final int userId;
    private final String phoneNumber;
    private final int dietPlanId;

    public UserWithDietPlan(String name, int userId, String phoneNumber, int dietPlanId){
        this.name = name;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.dietPlanId = dietPlanId;
    }

    public String getName() {
        return name;
    }
    public int getUserId() {
        return userId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getDietPlanId() { return dietPlanId; }
}
