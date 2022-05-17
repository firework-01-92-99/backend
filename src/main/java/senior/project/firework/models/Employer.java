package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employer")
public class Employer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idEmployer")
  private long idEmployer;
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
  @Column(name = "email")
  private String email;
  @Column(name = "lineId")
  private String lineId;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "businessType_idBusinessType")
  private Businesstype businesstype;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_idAccount")
  @JsonIgnore
  private Account account;
  @OneToOne(mappedBy = "employer")
  private Location location;
  @OneToMany(mappedBy = "employer")
  private List<Position> positionList;
  @OneToMany(mappedBy = "employer")
  private List<Ratings> ratingsList;
  @OneToMany(mappedBy = "employer")
  private List<Posting> postingList;
  @ManyToOne
  @JoinColumn(name = "province_idProvince")
  private Province province;
  @ManyToOne
  @JoinColumn(name = "district_idDistrict")
  private District district;
  @ManyToOne
  @JoinColumn(name = "sub_district_idSubdistrict")
  private SubDistrict subDistrict;
  @OneToMany(mappedBy = "employer")
  private List<LocationPic> locationPicList;

  public long getIdEmployer() {
    return idEmployer;
  }

  public void setIdEmployer(long idEmployer) {
    this.idEmployer = idEmployer;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public Businesstype getBusinesstype() {
    return businesstype;
  }

  public void setBusinesstype(Businesstype businesstype) {
    this.businesstype = businesstype;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public List<Position> getPositionList() {
    return positionList;
  }

  public void setPositionList(List<Position> positionList) {
    this.positionList = positionList;
  }

  public List<Ratings> getRatingsList() {
    return ratingsList;
  }

  public void setRatingsList(List<Ratings> ratingsList) {
    this.ratingsList = ratingsList;
  }

  public List<Posting> getPostingList() {
    return postingList;
  }

  public void setPostingList(List<Posting> postingList) {
    this.postingList = postingList;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public SubDistrict getSubDistrict() {
    return subDistrict;
  }

  public void setSubDistrict(SubDistrict subDistrict) {
    this.subDistrict = subDistrict;
  }

  public List<LocationPic> getLocationPicList() {
    return locationPicList;
  }

  public void setLocationPicList(List<LocationPic> locationPicList) {
    this.locationPicList = locationPicList;
  }
}
