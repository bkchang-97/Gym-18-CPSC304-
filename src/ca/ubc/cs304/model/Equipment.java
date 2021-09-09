package ca.ubc.cs304.model;

public class Equipment {
    private final String name;
    private final int equipmentId;

    public Equipment(String name, int equipmentId) {
        this.name = name;
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return this.name;
    }

    public int getEquipmentID() {
        return this.equipmentId;
    }
}
