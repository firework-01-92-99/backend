package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.Day;

import java.util.List;

public interface repoDay extends JpaRepository<Day,Long> {

    @Query(value = "SELECT * FROM day where idDay in (2,3,4,5,6)" ,nativeQuery = true)
    List<Day> getMondayToFriday();
}
