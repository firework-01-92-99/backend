package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Position;

import java.util.List;

public interface repoPosition extends JpaRepository<Position,Long> {
    List<Position> findByEmployer(Employer employer);
}
