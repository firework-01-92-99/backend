package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "approve")
public class Approve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idApprove")
  private long idApprove;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "admin_idAdmin")
  private Admin admin;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "status_idStatus")
  private Status status;
  @OneToOne(mappedBy = "approve")
  @JsonIgnore
  private Account account;

  public Approve(Admin admin, Status status, Account account) {
    this.admin = admin;
    this.status = status;
    this.account = account;
  }

  public Approve(long idApprove, Admin admin, Status status, Account account) {
    this.idApprove = idApprove;
    this.admin = admin;
    this.status = status;
    this.account = account;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public long getIdApprove() {
    return idApprove;
  }

  public void setIdApprove(long idApprove) {
    this.idApprove = idApprove;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}
