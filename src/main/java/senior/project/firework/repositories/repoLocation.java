package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Location;

public interface repoLocation extends JpaRepository<Location,Long> {
}
