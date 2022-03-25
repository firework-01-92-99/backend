package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
public class Favorite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idFavoritel")
  private long idFavoritel;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "worker_idWorker")
  private Worker worker;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "posting_idPosting")
  private Posting posting;

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public Posting getPosting() {
    return posting;
  }

  public void setPosting(Posting posting) {
    this.posting = posting;
  }

  public long getIdFavoritel() {
    return idFavoritel;
  }

  public void setIdFavoritel(long idFavoritel) {
    this.idFavoritel = idFavoritel;
  }

}
