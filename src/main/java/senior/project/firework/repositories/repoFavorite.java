package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Favorite;

public interface repoFavorite extends JpaRepository<Favorite,Long> {
}
