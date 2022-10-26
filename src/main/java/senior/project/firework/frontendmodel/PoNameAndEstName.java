package senior.project.firework.frontendmodel;

public class PoNameAndEstName {
    private long idApplication;
    private String positionName;
    private String establishmentName;

    public PoNameAndEstName(){

    }

    public PoNameAndEstName(long idApplication, String positionName, String establishmentName) {
        this.idApplication = idApplication;
        this.positionName = positionName;
        this.establishmentName = establishmentName;
    }

    public long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(long idApplication) {
        this.idApplication = idApplication;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }
}
