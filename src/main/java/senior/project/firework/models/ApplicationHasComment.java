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
  @OneToOne(mappedBy = "applicationHasComment")
  @JsonIgnore
  private Application application;

  public ApplicationHasComment(){

  }

  public ApplicationHasComment(String description, Application application) {
    this.description = description;
    this.application = application;
  }

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

  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
}
