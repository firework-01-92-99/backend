package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Ratings;
import senior.project.firework.models.Worker;

import java.util.List;

public interface repoRatings extends JpaRepository<Ratings,Long> {
    List<Ratings> findByWorker(Worker worker);
}
