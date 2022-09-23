package senior.project.firework.frontendmodel;

import senior.project.firework.models.Nationality;
import senior.project.firework.models.WorkerType;

public class WhoApplication {
    private long count;
    private long workerId;
    private String identificationNumber;
    private String verifyPic;
    private String sex;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private WorkerType workerType;
    private Nationality nationality;
    private String statusName;

    public WhoApplication(long count, long workerId, String identificationNumber, String verifyPic, String sex, String firstName, String middleName, String lastName, String phone, WorkerType workerType, Nationality nationality, String statusName) {
        this.count = count;
        this.workerId = workerId;
        this.identificationNumber = identificationNumber;
        this.verifyPic = verifyPic;
        this.sex = sex;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.workerType = workerType;
        this.nationality = nationality;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
