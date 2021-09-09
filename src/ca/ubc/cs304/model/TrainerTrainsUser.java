package ca.ubc.cs304.model;

public class TrainerTrainsUser {
    private final int userId;
    private final int trainerId;

    public TrainerTrainsUser(int userId, int trainerId) {
        this.userId = userId;
        this.trainerId = trainerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTrainerId() {
        return trainerId;
    }
}
