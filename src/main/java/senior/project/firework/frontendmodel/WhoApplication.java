package senior.project.firework.frontendmodel;

import senior.project.firework.models.Nationality;
import senior.project.firework.models.WorkerType;

import java.time.ZonedDateTime;

public class WhoApplication {
    private long count;
    private long applicationId;
    private String establishmentName;
    private String entrepreneurfName;
    private String entrepreneurlName;
    private String address;
    private String provinceName;
    private String districtName;
    private String subDistrictName;
    private String postcode;
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
    private String description;
    private String descriptionRejectOnWeb;
    private String descriptionRejectOnSite;
    private String descriptionBreakShort;

    public WhoApplication(long count, long applicationId, String establishmentName, String entrepreneurfName, String entrepreneurlName, String address, String provinceName, String districtName, String subDistrictName, String postcode, long round, ZonedDateTime date, long workerId, Double rate, String identificationNumber, String verifyPic, String sex, String firstName, String middleName, String lastName, String phone, WorkerType workerType, Nationality nationality, long idStatus, String statusName, long idStatusAdmin, String statusAdminName) {
        this.count = count;
        this.applicationId = applicationId;
        this.establishmentName = establishmentName;
        this.entrepreneurfName = entrepreneurfName;
        this.entrepreneurlName = entrepreneurlName;
        this.address = address;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.subDistrictName = subDistrictName;
        this.postcode = postcode;
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

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getEntrepreneurfName() {
        return entrepreneurfName;
    }

    public void setEntrepreneurfName(String entrepreneurfName) {
        this.entrepreneurfName = entrepreneurfName;
    }

    public String getEntrepreneurlName() {
        return entrepreneurlName;
    }

    public void setEntrepreneurlName(String entrepreneurlName) {
        this.entrepreneurlName = entrepreneurlName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public void setSubDistrictName(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRejectOnWeb() {
        return descriptionRejectOnWeb;
    }

    public void setDescriptionRejectOnWeb(String descriptionRejectOnWeb) {
        this.descriptionRejectOnWeb = descriptionRejectOnWeb;
    }

    public String getDescriptionRejectOnSite() {
        return descriptionRejectOnSite;
    }

    public void setDescriptionRejectOnSite(String descriptionRejectOnSite) {
        this.descriptionRejectOnSite = descriptionRejectOnSite;
    }

    public String getDescriptionBreakShort() {
        return descriptionBreakShort;
    }

    public void setDescriptionBreakShort(String descriptionBreakShort) {
        this.descriptionBreakShort = descriptionBreakShort;
    }
}
