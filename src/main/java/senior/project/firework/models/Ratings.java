package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

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
  private LocalDate timestamp;
  @Column(name = "for_who")
  private String forwho;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "employer_idEmployer")
  private Employer employer;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "worker_idWorker")
  private Worker worker;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "posting_idPosting")
  private Posting posting;

  public Ratings(long rate, String comment, LocalDate timestamp, String forwho, Employer employer, Worker worker, Posting posting) {
    this.rate = rate;
    this.comment = comment;
    this.timestamp = timestamp;
    this.forwho = forwho;
    this.employer = employer;
    this.worker = worker;
    this.posting = posting;
  }

  public Ratings() {

  }

  public String getFor_who() {
    return forwho;
  }

  public void setFor_who(String for_who) {
    this.forwho = for_who;
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

  public LocalDate getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDate timestamp) {
    this.timestamp = timestamp;
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

  public String getForwho() {
    return forwho;
  }

  public void setForwho(String forwho) {
    this.forwho = forwho;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }
}
