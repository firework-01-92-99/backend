package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.Posting;
import senior.project.firework.models.PostingHasDay;

import java.util.List;

public interface repoPostingHasDay extends JpaRepository<PostingHasDay,Long> {

    List<PostingHasDay> getPostingHasDayByPosting(Posting posting);
}
