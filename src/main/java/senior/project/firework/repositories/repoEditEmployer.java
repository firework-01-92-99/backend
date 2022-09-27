package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.EditEmployer;
import senior.project.firework.models.Employer;

public interface repoEditEmployer extends JpaRepository<EditEmployer,Long> {

    @Query(value = "SELECT MAX(idEditEmployer) FROM EditEmployer WHERE employer = ?1")
    long getMaxIdEmployerByEmployer(Employer employer);
}
