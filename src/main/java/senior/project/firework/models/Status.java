package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
  @JsonIgnore
  private List<Approve> approveList;
  @OneToMany(mappedBy = "status")
  @JsonIgnore
  private List<Application> applicationList;
  @OneToMany(mappedBy = "status")
  @JsonIgnore
  private List<Posting> postingList;

  public Status() {

  }

  public List<Approve> getApproveList() {
    return approveList;
  }

  public List<Application> getApplicationList() {
    return applicationList;
  }

  public List<Posting> getPostingList() {
    return postingList;
  }

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
