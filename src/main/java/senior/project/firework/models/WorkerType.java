package senior.project.firework.models;

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
  private List<Worker> workerList;
  @OneToMany(mappedBy = "workerType")
  private List<Posting> postingList;

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
