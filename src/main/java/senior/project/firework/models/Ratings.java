package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ratings")
public class Ratings {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idRating")
  private long idRating;
  @Column(name = "rate")
  private long rate;
  @Column(name = "comment")
  private String comment;
  @Column(name = "timestamp")
  private java.sql.Date timestamp;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "employer_idEmployer")
  private Employer employer;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "worker_idWorker")
  private Worker worker;

  public Ratings(long rate, String comment, Date timestamp, Employer employer, Worker worker) {
    this.rate = rate;
    this.comment = comment;
    this.timestamp = timestamp;
    this.employer = employer;
    this.worker = worker;
  }

  public Ratings() {

  }

  public long getIdRating() {
    return idRating;
  }

  public void setIdRating(long idRating) {
    this.idRating = idRating;
  }

  public long getRate() {
    return rate;
  }

  public void setRate(long rate) {
    this.rate = rate;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public java.sql.Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(java.sql.Date timestamp) {
    this.timestamp = timestamp;
  }


}
