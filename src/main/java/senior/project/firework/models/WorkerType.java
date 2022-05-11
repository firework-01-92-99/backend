package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "worker_type")
public class WorkerType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idWorkerType")
  private long idWorkerType;
  @Column(name = "typeName")
  private String typeName;
  @OneToMany(mappedBy = "workerType")
  @JsonIgnore
  private List<Worker> workerList;
  @OneToMany(mappedBy = "workerType")
  @JsonIgnore
  private List<Posting> postingList;

  public List<Worker> getWorkerList() {
    return workerList;
  }

  public List<Posting> getPostingList() {
    return postingList;
  }

  public void setPostingList(List<Posting> postingList) {
    this.postingList = postingList;
  }

  public long getIdWorkerType() {
    return idWorkerType;
  }

  public void setIdWorkerType(long idWorkerType) {
    this.idWorkerType = idWorkerType;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public void setWorkerList(List<Worker> workerList) {
    this.workerList = workerList;
  }
}
