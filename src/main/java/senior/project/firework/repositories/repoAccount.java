package senior.project.firework.repositories;

import senior.project.firework.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repoAccount extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
}
