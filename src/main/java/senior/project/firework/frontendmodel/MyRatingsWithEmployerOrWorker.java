package senior.project.firework.frontendmodel;

import java.time.LocalDate;

public class MyRatingsWithEmployerOrWorker {
    private long idRatings;
    private long rate;
    private String comment;
    private LocalDate timestamp;
    private String employerNameOrWorkerName;

    public MyRatingsWithEmployerOrWorker(){

    }

    public MyRatingsWithEmployerOrWorker(long idRatings, long rate, String comment, LocalDate timestamp, String employerNameOrWorkerName) {
        this.idRatings = idRatings;
        this.rate = rate;
        this.comment = comment;
        this.timestamp = timestamp;
        this.employerNameOrWorkerName = employerNameOrWorkerName;
    }

    public long getIdRatings() {
        return idRatings;
    }

    public void setIdRatings(long idRatings) {
        this.idRatings = idRatings;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getEmployerNameOrWorkerName() {
        return employerNameOrWorkerName;
    }

    public void setEmployerNameOrWorkerName(String employerNameOrWorkerName) {
        this.employerNameOrWorkerName = employerNameOrWorkerName;
    }
}
