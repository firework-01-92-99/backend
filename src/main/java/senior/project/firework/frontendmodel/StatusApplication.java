package senior.project.firework.frontendmodel;

import senior.project.firework.models.ActToRegister;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class StatusApplication {
    private long idApplication;
    private String establishmentName;
    private String positionName;
    private String address;
    private String provinceName;
    private String districtName;
    private String subDistrict;
    private String postcode;
    private String statusName;
    private ZonedDateTime date;
    private ActToRegister actToRegister;

    public StatusApplication(){

    }

    public StatusApplication(long idApplication, String establishmentName, String positionName, String address, String provinceName, String districtName, String subDistrict, String postcode, String statusName, ZonedDateTime date, ActToRegister actToRegister) {
        this.idApplication = idApplication;
        this.establishmentName = establishmentName;
        this.positionName = positionName;
        this.address = address;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.subDistrict = subDistrict;
        this.postcode = postcode;
        this.statusName = statusName;
        this.date = date;
        this.actToRegister = actToRegister;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(long idApplication) {
        this.idApplication = idApplication;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public ActToRegister getActToRegister() {
        return actToRegister;
    }

    public void setActToRegister(ActToRegister actToRegister) {
        this.actToRegister = actToRegister;
    }
}
