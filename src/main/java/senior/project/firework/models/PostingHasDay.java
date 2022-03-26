package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "posting_has_day")
public class PostingHasDay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idPostingHasDay")
  private long idPostingHasDay;
  @ManyToOne
  @JoinColumn(name = "day_idDay")
  private Day day;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "posting_idPosting")
  private Posting posting;

  public long getIdPostingHasDay() {
    return idPostingHasDay;
  }

  public void setIdPostingHasDay(long idPostingHasDay) {
    this.idPostingHasDay = idPostingHasDay;
  }

  public Day getDay() {
    return day;
  }

  public void setDay(Day day) {
    this.day = day;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }
}
