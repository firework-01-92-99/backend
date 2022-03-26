package senior.project.firework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idRole")
  private long idRole;
  @Column(name = "roleName")
  private String roleName;
  @OneToMany(mappedBy = "role")
  private List<Account> accountList;
  @OneToMany(mappedBy = "role")
  private List<Admin> adminList;

  public void setAccountList(List<Account> accountList) {
    this.accountList = accountList;
  }

  public long getIdRole() {
    return idRole;
  }

  public void setIdRole(long idRole) {
    this.idRole = idRole;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
