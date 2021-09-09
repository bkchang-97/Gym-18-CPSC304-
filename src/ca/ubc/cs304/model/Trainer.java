package ca.ubc.cs304.model;

import java.util.HashSet;


public class Trainer {
    private  String name;
    private final int trainerId;
    private String phoneNumber;

    public Trainer(String name, int trainerId, String phoneNumber){
        this.name = name;
        this.trainerId = trainerId;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
