package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.WorkerType;

public interface repoWorkerType extends JpaRepository<WorkerType,Long> {
}
