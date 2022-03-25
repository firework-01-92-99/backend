package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Ratings;

public interface repoRatings extends JpaRepository<Ratings,Long> {
}
