package senior.project.firework.frontendmodel;

import senior.project.firework.models.Posting;
import senior.project.firework.models.Worker;
import senior.project.firework.models.Employer;

public class WhatFavorite {
    private long idWhatFavorite;
    private Worker worker;
    private Posting posting;
    private Employer employer;

    public WhatFavorite(){

    }

    public WhatFavorite(long idWhatFavorite, Worker worker, Posting posting, Employer employer) {
        this.idWhatFavorite = idWhatFavorite;
        this.worker = worker;
        this.posting = posting;
        this.employer = employer;
    }

    public long getIdWhatFavorite() {
        return idWhatFavorite;
    }

    public void setIdWhatFavorite(long idWhatFavorite) {
        this.idWhatFavorite = idWhatFavorite;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
