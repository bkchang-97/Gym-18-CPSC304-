package ca.ubc.cs304.model;

public class CertificateWithTrainer {
    private final int certId;
    private final String name;
    private final int trainerId;

    public CertificateWithTrainer(int certId, String name, int trainerId) {
        this.certId = certId;
        this.name = name;
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public int getCertId() {
        return certId;
    }

    public int getTrainerId() {
        return trainerId;
    }
}
