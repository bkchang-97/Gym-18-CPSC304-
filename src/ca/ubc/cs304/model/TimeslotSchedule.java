package ca.ubc.cs304.model;

import java.util.Date;

public class TimeslotSchedule {
    private final Date time;
    private final int userId;
    private final int trainerId;

    public TimeslotSchedule(Date time, int userId, int trainerId) {
        this.time = time;
        this.userId = userId;
        this.trainerId = trainerId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getTime() {
        return time;
    }
}
