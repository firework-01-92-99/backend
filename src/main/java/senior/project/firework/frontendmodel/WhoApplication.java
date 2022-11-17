package senior.project.firework.frontendmodel;

import senior.project.firework.models.Nationality;
import senior.project.firework.models.WorkerType;

import java.time.ZonedDateTime;

public class WhoApplication {
    private long count;
    private long applicationId;
    private long round;
    private ZonedDateTime date;
    private long workerId;
    private Double rate;
    private String identificationNumber;
    private String verifyPic;
    private String sex;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private WorkerType workerType;
    private Nationality nationality;
    private long idStatus;
    private String statusName;
    private long idStatusAdmin;
    private String statusAdminName;
    private String comment;

    public WhoApplication(long count, long applicationId, long round, ZonedDateTime date,long workerId, Double rate, String identificationNumber, String verifyPic, String sex, String firstName, String middleName, String lastName, String phone, WorkerType workerType, Nationality nationality, long idStatus, String statusName, long idStatusAdmin ,String statusAdminName,String comment) {
        this.count = count;
        this.applicationId = applicationId;
        this.round = round;
        this.date = date;
        this.workerId = workerId;
        this.rate = rate;
        this.identificationNumber = identificationNumber;
        this.verifyPic = verifyPic;
        this.sex = sex;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.workerType = workerType;
        this.nationality = nationality;
        this.idStatus = idStatus;
        this.statusName = statusName;
        this.idStatusAdmin = idStatusAdmin;
        this.statusAdminName = statusAdminName;
        this.comment = comment;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getRound() {
        return round;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getVerifyPic() {
        return verifyPic;
    }

    public void setVerifyPic(String verifyPic) {
        this.verifyPic = verifyPic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public WorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(WorkerType workerType) {
        this.workerType = workerType;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getIdStatusAdmin() {
        return idStatusAdmin;
    }

    public String getStatusAdminName() {
        return statusAdminName;
    }

    public void setStatusAdminName(String statusAdminName) {
        this.statusAdminName = statusAdminName;
    }

    public void setIdStatusAdmin(long idStatusAdmin) {
        this.idStatusAdmin = idStatusAdmin;
    }
}
