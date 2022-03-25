package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "application_has_comment")
public class ApplicationHasComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idHasComment")
  private long idHasComment;
  @Column(name = "description")
  private String description;
  @OneToMany(mappedBy = "applicationHasComment")
  @JsonIgnore
  private List<Application> applicationList;

  public long getIdHasComment() {
    return idHasComment;
  }

  public void setIdHasComment(long idHasComment) {
    this.idHasComment = idHasComment;
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
}
