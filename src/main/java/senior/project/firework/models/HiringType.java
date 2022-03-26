package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hiring_type")
public class HiringType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idHiringtype")
  private long idHiringtype;
  @Column(name = "nameType")
  private String nameType;
  @OneToMany(mappedBy = "hiringType")
  @JsonIgnore
  private List<Posting> posting;

  public long getIdHiringtype() {
    return idHiringtype;
  }

  public void setIdHiringtype(long idHiringtype) {
    this.idHiringtype = idHiringtype;
  }

  public String getNameType() {
    return nameType;
  }

  public void setNameType(String nameType) {
    this.nameType = nameType;
  }

  public void setPosting(List<Posting> posting) {
    this.posting = posting;
  }
}
