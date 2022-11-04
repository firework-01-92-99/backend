package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.EditEmployer;
import senior.project.firework.models.Employer;

import java.util.List;

public interface repoEditEmployer extends JpaRepository<EditEmployer,Long> {

    @Query(value = "SELECT MAX(idEditEmployer) FROM EditEmployer WHERE employer = ?1")
    long getMaxIdEmployerByEmployer(Employer employer);

    @Query(value = "SELECT ee FROM EditEmployer ee,Employer e WHERE ee.employer.idEmployer = e.idEmployer " +
            "AND e.idEmployer = ?1 ")
    List<EditEmployer> getEditEmployerByIdEmployer(long idEmployer);
}
