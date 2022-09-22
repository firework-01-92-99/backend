package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Favorite;
import senior.project.firework.models.Worker;

import java.util.List;

public interface repoFavorite extends JpaRepository<Favorite,Long> {
    List<Favorite> findByWorker(Worker worker);
}
