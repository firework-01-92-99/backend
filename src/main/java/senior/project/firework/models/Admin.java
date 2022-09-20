package senior.project.firework.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idAdmin")
  private long idAdmin;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Column(name = "firstName")
  private String firstName;
  @Column(name = "lastName")
  private String lastName;
  @OneToMany(mappedBy = "admin")
  private List<Approve> approveList;
  @OneToMany(mappedBy = "admin")
  private List<Application> applicationList;
  @OneToMany(mappedBy = "admin")
  private List<AdminHasAct> adminHasActList;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "role_idRole")
  private Role role;


  public Admin(long idAdmin, String email, String password, String firstName, String lastName, List<Approve> approveList, List<Application> applicationList, Role role) {
    this.idAdmin = idAdmin;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.approveList = approveList;
    this.applicationList = applicationList;
    this.role = role;
  }

  public Admin() {

  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Application> getApplicationList() {
    return applicationList;
  }

  public void setApplicationList(List<Application> applicationList) {
    this.applicationList = applicationList;
  }

  public List<Approve> getApproveList() {
    return approveList;
  }

  public void setApproveList(List<Approve> approveList) {
    this.approveList = approveList;
  }

  public long getIdAdmin() {
    return idAdmin;
  }

  public void setIdAdmin(long idAdmin) {
    this.idAdmin = idAdmin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<AdminHasAct> getAdminHasActList() {
    return adminHasActList;
  }

  public void setAdminHasActList(List<AdminHasAct> adminHasActList) {
    this.adminHasActList = adminHasActList;
  }
}
