package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAccount")
  private long idAccount;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "role_idRole")
  private Role role;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "approve_idApprove")
  @JsonIgnore
  private Approve approve;
  @OneToOne(mappedBy = "account")
  private Employer employer;
  @OneToOne(mappedBy = "account")
  private Worker worker;

  public long getIdAccount() {
    return idAccount;
  }

  public void setIdAccount(long idAccount) {
    this.idAccount = idAccount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Approve getApprove() {
    return approve;
  }

  public void setApprove(Approve approve) {
    this.approve = approve;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }
}
