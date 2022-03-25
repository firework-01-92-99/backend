package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Worker;

public interface repoWorker extends JpaRepository<Worker,Long> {
}
