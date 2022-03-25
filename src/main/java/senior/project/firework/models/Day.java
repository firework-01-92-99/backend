package senior.project.firework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "day")
public class Day {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idDay")
  private long idDay;
  @Column(name = "dayName")
  private String dayName;
  @Column(name = "abbreviation")
  private String abbreviation;
  @OneToMany(mappedBy = "day")
  private List<PostingHasDay> postingHasDayList;

  public long getIdDay() {
    return idDay;
  }

  public void setIdDay(long idDay) {
    this.idDay = idDay;
  }

  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public void setPostingHasDayList(List<PostingHasDay> postingHasDayList) {
    this.postingHasDayList = postingHasDayList;
  }
}
