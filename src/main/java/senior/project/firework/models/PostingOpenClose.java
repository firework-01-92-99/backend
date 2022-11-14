package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posting_open_close")
public class PostingOpenClose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPostingOpenClose")
    private long idPostingOpenClose;
    @Column(name = "activeDate")
    private Date activeDate;
    @Column(name = "inactiveDate")
    private Date inactiveDate;
    @Column(name = "round")
    private long round;
    @ManyToOne
    @JoinColumn(name = "posting_idPosting")
    @JsonIgnore
    private Posting posting;

    public PostingOpenClose() {

    }

    public PostingOpenClose(Date activeDate, long round, Posting posting) {
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

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
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
