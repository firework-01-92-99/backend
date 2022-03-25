package senior.project.firework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "province")
public class Province {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idProvince")
  private String idProvince;
  @Column(name = "provinceName")
  private String provinceName;
  @OneToMany(mappedBy = "province")
  private List<District> districtList;
  @OneToMany(mappedBy = "province")
  private List<Employer> employerList;

  public String getIdProvince() {
    return idProvince;
  }

  public void setIdProvince(String idProvince) {
    this.idProvince = idProvince;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public List<District> getDistrictList() {
    return districtList;
  }

  public void setDistrictList(List<District> districtList) {
    this.districtList = districtList;
  }

  public void setEmployerList(List<Employer> employerList) {
    this.employerList = employerList;
  }
}
