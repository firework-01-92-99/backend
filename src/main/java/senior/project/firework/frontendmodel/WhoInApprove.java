package senior.project.firework.frontendmodel;

public class WhoInApprove {
    private long count;
    private long idApprove;
    private String name;
    private String workOrEmp;
    private String nationlity;
    private String status;

    public WhoInApprove(){

    }

    public WhoInApprove(long count, long idApprove, String name, String workOrEmp, String nationlity, String status) {
        this.count = count;
        this.idApprove = idApprove;
        this.name = name;
        this.workOrEmp = workOrEmp;
        this.nationlity = nationlity;
        this.status = status;
    }

    public long getIdApprove() {
        return idApprove;
    }

    public void setIdApprove(long idApprove) {
        this.idApprove = idApprove;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkOrEmp() {
        return workOrEmp;
    }

    public void setWorkOrEmp(String workOrEmp) {
        this.workOrEmp = workOrEmp;
    }

    public String getNationlity() {
        return nationlity;
    }

    public void setNationlity(String nationlity) {
        this.nationlity = nationlity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
