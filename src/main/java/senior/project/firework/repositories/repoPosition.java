package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Position;
import senior.project.firework.models.Status;

import java.util.List;

public interface repoPosition extends JpaRepository<Position,Long> {
    Position findByPositionName(String positionName);

    List<Position> findByStatusOrderByPositionNameAsc(Status status);
}
