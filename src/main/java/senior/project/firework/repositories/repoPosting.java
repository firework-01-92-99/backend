package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Posting;

public interface repoPosting extends JpaRepository<Posting,Long> {
}
