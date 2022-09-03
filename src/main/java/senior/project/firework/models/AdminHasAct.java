package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "admin_has_act")
public class AdminHasAct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdminHasAct")
    private long idAdminHasAct;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "act_to_registrar_idaction")
    private ActToRegister actToRegister;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_idAdmin")
    private Admin admin;

    public long getIdAdminHasAct() {
        return idAdminHasAct;
    }

    public void setIdAdminHasAct(long idAdminHasAct) {
        this.idAdminHasAct = idAdminHasAct;
    }

    public ActToRegister getActToRegister() {
        return actToRegister;
    }

    public void setActToRegister(ActToRegister actToRegister) {
        this.actToRegister = actToRegister;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
