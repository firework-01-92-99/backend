package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Ratings;
import senior.project.firework.models.Worker;

import java.util.List;

public interface repoRatings extends JpaRepository<Ratings,Long> {

    Ratings findByWorkerAndEmployerAndForwho(Worker worker,Employer employer,String for_who);

    List<Ratings> findByWorkerAndForwho(Worker worker,String for_who);

    List<Ratings> findByEmployerAndForwho(Employer employer,String for_who);
}
