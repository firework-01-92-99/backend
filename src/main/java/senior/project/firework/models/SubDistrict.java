package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "sub_district")
public class SubDistrict {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idSubdistrict")
  private String idSubdistrict;
  @Column(name = "subDistrict")
  private String subDistrict;
  @Column(name = "postcode")
  private String postcode;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "district_idDistrict")
  private District district;

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public String getIdSubdistrict() {
    return idSubdistrict;
  }

  public void setIdSubdistrict(String idSubdistrict) {
    this.idSubdistrict = idSubdistrict;
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


}
