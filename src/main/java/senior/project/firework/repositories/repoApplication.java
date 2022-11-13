package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.Application;
import senior.project.firework.models.Status;

import java.util.List;

public interface repoApplication extends JpaRepository<Application,Long> {

    @Query(value = "select a from Application a,Worker w where a.worker.idWorker = w.idWorker and a.idWorker = :idWorker")
    List<Application> selectByIdworker(String idWorker);

    List<Application> findByStatus(Status status);
}
