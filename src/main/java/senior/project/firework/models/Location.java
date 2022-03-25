package senior.project.firework.models;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idLocation")
  private long idLocation;
  @Column(name = "latitude")
  private double latitude;
  @Column(name = "longitude")
  private double longitude;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employer_idEmployer")
  private Employer employer;

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public long getIdLocation() {
    return idLocation;
  }

  public void setIdLocation(long idLocation) {
    this.idLocation = idLocation;
  }


  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }


  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

}
