package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idposition")
  private long idposition;
  @Column(name = "positionName")
  private String positionName;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "posting_idPosting")
  @JsonIgnore
  private Posting posting;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "employer_idEmployer")
  private Employer employer;

  public long getIdposition() {
    return idposition;
  }

  public void setIdposition(long idposition) {
    this.idposition = idposition;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }
}
