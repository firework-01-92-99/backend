package senior.project.firework.frontendmodel;

public class WhoApplication {
    private long count;
    private long workerId;
    private String statusName;

    public WhoApplication(long count, long workerId,String statusName) {
        this.count = count;
        this.workerId = workerId;
        this.statusName = statusName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
