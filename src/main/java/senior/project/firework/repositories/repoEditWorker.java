package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.EditWorker;
import senior.project.firework.models.Worker;

import java.util.List;

public interface repoEditWorker extends JpaRepository<EditWorker,Long> {

    @Query(value = "SELECT MAX(idEditWorker) FROM EditWorker WHERE worker = ?1")
    long getMaxIdWorkerByWorker(Worker worker);

    @Query(value = "SELECT ew FROM EditWorker ew,Worker w WHERE ew.worker.idWorker = w.idWorker " +
            "AND w.idWorker = ?1 ")
    List<EditWorker> getEditWorkerByIdWorker(long idWorker);
}
