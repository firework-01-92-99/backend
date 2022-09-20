package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Admin;

public interface repoAdmin extends JpaRepository<Admin,Long> {
    Admin findByEmail(String email);
}
