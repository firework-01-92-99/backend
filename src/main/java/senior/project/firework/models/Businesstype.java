package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "businessType")
public class Businesstype {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idBusinessType")
  private long idBusinessType;
  @Column(name = "nameType")
  private String nameType;
  @OneToMany(mappedBy = "businessType")
  @JsonIgnore
  private List<Employer> employerList;

  public void setEmployerList(List<Employer> employerList) {
    this.employerList = employerList;
  }

  public long getIdBusinessType() {
    return idBusinessType;
  }

  public void setIdBusinessType(long idBusinessType) {
    this.idBusinessType = idBusinessType;
  }

  public String getNameType() {
    return nameType;
  }

  public void setNameType(String nameType) {
    this.nameType = nameType;
  }

}
