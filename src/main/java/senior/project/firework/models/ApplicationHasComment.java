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
  @Column(name = "descriptionRejectOnWeb")
  private String descriptionRejectOnWeb;
  @Column(name = "descriptionRejectOnSite")
  private String descriptionRejectOnSite;
  @Column(name = "descriptionBreakShort")
  private String descriptionBreakShort;
  @Column(name = "descriptionRejectSentWorker")
  private String descriptionRejectSentWorker;
  @OneToOne(mappedBy = "applicationHasComment")
  @JsonIgnore
  private Application application;

  public ApplicationHasComment(){

  }

  public ApplicationHasComment(Application application) {
    this.application = application;
  }

  public String getDescriptionRejectOnSite() {
    return descriptionRejectOnSite;
  }

  public void setDescriptionRejectOnSite(String descriptionRejectOnSite) {
    this.descriptionRejectOnSite = descriptionRejectOnSite;
  }

  public long getIdHasComment() {
    return idHasComment;
  }

  public void setIdHasComment(long idHasComment) {
    this.idHasComment = idHasComment;
  }

  public String getDescriptionRejectOnWeb() {
    return descriptionRejectOnWeb;
  }

  public void setDescriptionRejectOnWeb(String descriptionRejectOnWeb) {
    this.descriptionRejectOnWeb = descriptionRejectOnWeb;
  }

  public String getDescriptionBreakShort() {
    return descriptionBreakShort;
  }

  public void setDescriptionBreakShort(String descriptionBreakShort) {
    this.descriptionBreakShort = descriptionBreakShort;
  }

  public Application getApplication() {
    return application;
  }

  public String getDescriptionRejectSentWorker() {
    return descriptionRejectSentWorker;
  }

  public void setDescriptionRejectSentWorker(String descriptionRejectSentWorker) {
    this.descriptionRejectSentWorker = descriptionRejectSentWorker;
  }

  public void setApplication(Application application) {
    this.application = application;
  }
}
