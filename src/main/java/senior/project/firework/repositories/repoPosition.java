package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Position;

public interface repoPosition extends JpaRepository<Position,Long> {
}
