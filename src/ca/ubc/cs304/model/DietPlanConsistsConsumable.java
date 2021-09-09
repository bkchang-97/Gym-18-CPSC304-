package ca.ubc.cs304.model;

public class DietPlanConsistsConsumable {
    private final int planId;
    private final String name;
    private final double portionByGramOrMl;

    public DietPlanConsistsConsumable(int planId, String name, double portionByGramOrMl) {
        this.planId = planId;
        this.name = name;
        this.portionByGramOrMl = portionByGramOrMl;
    }

    public String getName() {
        return name;
    }

    public int getPlanId() {
        return planId;
    }

    public double getPortionByGramOrMl() {
        return portionByGramOrMl;
    }
}
