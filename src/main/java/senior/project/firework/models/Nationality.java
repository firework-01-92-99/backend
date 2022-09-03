package senior.project.firework.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nationality")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnationality")
    private long idnationality;
    @Column(name = "nationality_name")
    private String nationality_name;
    @OneToMany(mappedBy = "nationality")
    @JsonIgnore
    private List<Worker> workerList;
    @OneToMany(mappedBy = "nationality")
    @JsonIgnore
    private List<Employer> employerList;

    public long getIdnationality() {
        return idnationality;
    }

    public void setIdnationality(long idnationality) {
        this.idnationality = idnationality;
    }

    public String getNationality_name() {
        return nationality_name;
    }

    public void setNationality_name(String nationality_name) {
        this.nationality_name = nationality_name;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    public List<Employer> getEmployerList() {
        return employerList;
    }

    public void setEmployerList(List<Employer> employerList) {
        this.employerList = employerList;
    }
}
