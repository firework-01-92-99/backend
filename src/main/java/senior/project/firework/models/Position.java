package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idposition")
  private long idposition;
  @Column(name = "positionName")
  private String positionName;
  @OneToMany(mappedBy = "position")
  @JsonIgnore
  private List<Posting> posting;
  @ManyToOne
  @JoinColumn(name = "status_idStatus")
  private Status status;

  public Position(){

  }

  public Position(String positionName, Status status) {
    this.positionName = positionName;
    this.status = status;
  }

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

  public List<Posting> getPosting() {
    return posting;
  }

  public void setPosting(List<Posting> posting) {
    this.posting = posting;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
