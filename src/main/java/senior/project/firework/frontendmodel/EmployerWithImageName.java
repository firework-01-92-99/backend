package senior.project.firework.frontendmodel;

public class EmployerWithImageName {
    private long idEmployer;
    private String imageName;

    public EmployerWithImageName(){

    }

    public EmployerWithImageName(long idEmployer, String imageName) {
        this.idEmployer = idEmployer;
        this.imageName = imageName;
    }

    public long getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(long idEmployer) {
        this.idEmployer = idEmployer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
