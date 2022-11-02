package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "edit_employer")
public class EditEmployer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEditEmployer")
    private long idEditEmployer;
    @Column(name = "establishmentName")
    private String establishmentName;
    @Column(name = "entrepreneurfName")
    private String entrepreneurfName;
    @Column(name = "entrepreneurlName")
    private String entrepreneurlName;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;
    @Column(name = "phone")
    private String phone;
    @Column(name = "lineId")
    private String lineId;
    @Column(name = "profile")
    private String profile;
    @Column(name = "provinceName")
    private String provinceName;
    @Column(name = "districtName")
    private String districtName;
    @Column(name = "subDistrict")
    private String subDistrict;
    @Column(name = "postcode")
    private String postcode;
    @ManyToOne
    @JoinColumn(name = "employer_idEmployer")
    @JsonIgnore
    private Employer employer;

    public EditEmployer(){

    }

    public EditEmployer(String establishmentName, String entrepreneurfName, String entrepreneurlName, String address, String tel, String phone, String lineId, String profile, String provinceName, String districtName, String subDistrict, String postcode, Employer employer) {
        this.establishmentName = establishmentName;
        this.entrepreneurfName = entrepreneurfName;
        this.entrepreneurlName = entrepreneurlName;
        this.address = address;
        this.tel = tel;
        this.phone = phone;
        this.lineId = lineId;
        this.profile = profile;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.subDistrict = subDistrict;
        this.postcode = postcode;
        this.employer = employer;
    }

    public long getIdEditEmployer() {
        return idEditEmployer;
    }

    public void setIdEditEmployer(long idEditEmployer) {
        this.idEditEmployer = idEditEmployer;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
