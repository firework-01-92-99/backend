package senior.project.firework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class Status {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idStatus")
  private long idStatus;
  @Column(name = "statusName")
  private String statusName;
  @OneToMany(mappedBy = "status")
  private List<Approve> approveList;
  @OneToMany(mappedBy = "status")
  private List<Application> applicationList;
  @OneToMany(mappedBy = "status")
  private List<Posting> postingList;

  public long getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(long idStatus) {
    this.idStatus = idStatus;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public void setApproveList(List<Approve> approveList) {
    this.approveList = approveList;
  }

  public void setApplicationList(List<Application> applicationList) {
    this.applicationList = applicationList;
  }

  public void setPostingList(List<Posting> postingList) {
    this.postingList = postingList;
  }
}
