package senior.project.firework.repositories;

import senior.project.firework.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Worker;

public interface repoAccount extends JpaRepository<Account,Long> {
    Account findByEmail(String email);

    Account findByEmployer(Employer employer);

    Account findByWorker(Worker worker);
}
