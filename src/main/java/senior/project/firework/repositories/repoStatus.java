package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Status;

public interface repoStatus extends JpaRepository<Status,Long> {
}
