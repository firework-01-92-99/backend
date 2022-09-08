package senior.project.firework.frontendmodel;

import java.util.List;

public class HowManyApplication {
    private long idPosting;
    private List<WhoApplication> whoApplicationList;

    public HowManyApplication(long idPosting, List<WhoApplication> whoApplicationList) {
        this.idPosting = idPosting;
        this.whoApplicationList = whoApplicationList;
    }

    public long getIdPosting() {
        return idPosting;
    }

    public void setIdPosting(long idPosting) {
        this.idPosting = idPosting;
    }

    public List<WhoApplication> getWhoApplicationList() {
        return whoApplicationList;
    }

    public void setWhoApplicationList(List<WhoApplication> whoApplicationList) {
        this.whoApplicationList = whoApplicationList;
    }
}
