package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "application")
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idApplication")
  private long idApplication;
  @Column(name = "posting_idPosting" , updatable = false , insertable = false)
  private long idPosting;
  @Column(name = "worker_idWorker" , updatable = false , insertable = false)
  private long idWorker;
  @Column(name = "status_idStatus" , updatable = false , insertable = false)
  private long idStatus;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "worker_idWorker")
  private Worker worker;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "posting_idPosting")
  private Posting posting;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "admin_idAdmin")
  private Admin admin;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "application_has_comment_idHasComment")
  private ApplicationHasComment applicationHasComment;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "status_idStatus")
  private Status status;

  public Application() {

  }

  public Application(Worker worker, Posting posting,Status status) {
    this.worker = worker;
    this.posting = posting;
    this.status = status;
  }

  public long getIdPosting() {
    return idPosting;
  }

  public void setIdPosting(long idPosting) {
    this.idPosting = idPosting;
  }

  public long getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(long idWorker) {
    this.idWorker = idWorker;
  }

  public long getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(long idStatus) {
    this.idStatus = idStatus;
  }

  public long getIdApplication() {
    return idApplication;
  }

  public void setIdApplication(long idApplication) {
    this.idApplication = idApplication;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public ApplicationHasComment getApplicationHasComment() {
    return applicationHasComment;
  }

  public void setApplicationHasComment(ApplicationHasComment applicationHasComment) {
    this.applicationHasComment = applicationHasComment;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
