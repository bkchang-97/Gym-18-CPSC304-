package ca.ubc.cs304.model;

public class ExerciseWithEquipment {
    private final String name;
    private final int equipmentId;

    public ExerciseWithEquipment(String name, int equipmentId){
        this.name = name;
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public int getEquipmentId() {
        return equipmentId;
    }
}