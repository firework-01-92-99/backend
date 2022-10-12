package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "act_to_registrar")
public class ActToRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaction")
    private long idaction;
    @Column(name = "act_name")
    private String act_name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "actToRegister")
    @JsonIgnore
    private List<Application> applicationList;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_idAdmin")
    private Admin admin;

    public long getIdaction() {
        return idaction;
    }

    public void setIdaction(long idaction) {
        this.idaction = idaction;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
