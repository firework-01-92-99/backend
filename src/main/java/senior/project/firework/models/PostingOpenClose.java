package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "posting_open_close")
public class PostingOpenClose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPostingOpenClose")
    private long idPostingOpenClose;
    @Column(name = "activeDate")
    private ZonedDateTime activeDate;
    @Column(name = "inactiveDate")
    private ZonedDateTime inactiveDate;
    @Column(name = "round")
    private long round;
    @ManyToOne
    @JoinColumn(name = "posting_idPosting")
    @JsonIgnore
    private Posting posting;

    public PostingOpenClose() {

    }

    public PostingOpenClose(ZonedDateTime activeDate, long round, Posting posting) {
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

    public ZonedDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(ZonedDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public ZonedDateTime getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(ZonedDateTime inactiveDate) {
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
