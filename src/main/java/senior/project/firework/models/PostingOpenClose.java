package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "posting_open_close")
public class PostingOpenClose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPostingOpenClose")
    private long idPostingOpenClose;
    @Column(name = "activeDate")
    private LocalDate activeDate;
    @Column(name = "inactiveDate")
    private LocalDate inactiveDate;
    @Column(name = "round")
    private long round;
    @ManyToOne
    @JoinColumn(name = "posting_idPosting")
    @JsonIgnore
    private Posting posting;

    public PostingOpenClose() {

    }

    public PostingOpenClose(LocalDate activeDate, long round, Posting posting) {
        this.activeDate = activeDate;
        this.round = round;
        this.posting = posting;
    }

    public long getIdPostingOpenClose() {
        return idPostingOpenClose;
    }

    public void setIdPostingOpenClose(long idPostingOpenClose) {
        this.idPostingOpenClose = idPostingOpenClose;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDate activeDate) {
        this.activeDate = activeDate;
    }

    public LocalDate getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(LocalDate inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public long getRound() {
        return round;
    }

    public void setRound(long round) {
        this.round = round;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }
}
