package senior.project.firework.frontendmodel;

public class StatusApplication {
    private String establishmentName;
    private String positionName;
    private String provinceName;
    private String districtName;
    private String subDistrict;
    private String postcode;
    private String statusName;

    public StatusApplication(String establishmentName, String positionName, String provinceName, String districtName, String subDistrict, String postcode, String statusName) {
        this.establishmentName = establishmentName;
        this.positionName = positionName;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.subDistrict = subDistrict;
        this.postcode = postcode;
        this.statusName = statusName;
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
}
