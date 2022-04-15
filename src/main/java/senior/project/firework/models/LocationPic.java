package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "location_pic")
public class LocationPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPic")
    private long idPic;
    @Column(name = "locPic")
    private String locPic;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employer_idEmployer")
    private Employer employer;

    public long getIdPic() {
        return idPic;
    }

    public void setIdPic(long idPic) {
        this.idPic = idPic;
    }

    public String getLocPic() {
        return locPic;
    }

    public void setLocPic(String locPic) {
        this.locPic = locPic;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
