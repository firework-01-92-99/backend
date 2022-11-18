package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.Posting;
import senior.project.firework.models.PostingOpenClose;

import java.util.List;

public interface repoPostingOpenClose  extends JpaRepository<PostingOpenClose,Long> {
    @Query(value = "SELECT MAX(idPostingOpenClose) FROM PostingOpenClose WHERE posting = ?1")
    long getMAXIdPostingOpenCloseByPosting(Posting posting);

    List<PostingOpenClose> findByPosting(Posting posting);

    PostingOpenClose findByPostingAndRound(Posting posting,long round);
}
