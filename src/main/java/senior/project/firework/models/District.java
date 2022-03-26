package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
public class District {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idDistrict")
  private String idDistrict;
  @Column(name = "districtName")
  private String districtName;
  @OneToMany(mappedBy = "district")
  private List<SubDistrict> subDistrictList;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "province_idProvince")
  private Province province;
  @OneToMany(mappedBy = "district")
  @JsonIgnore
  private List<Employer> employerList;

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public String getIdDistrict() {
    return idDistrict;
  }

  public void setIdDistrict(String idDistrict) {
    this.idDistrict = idDistrict;
  }


  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public List<SubDistrict> getSubDistrictList() {
    return subDistrictList;
  }

  public void setSubDistrictList(List<SubDistrict> subDistrictList) {
    this.subDistrictList = subDistrictList;
  }
}
