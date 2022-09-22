package senior.project.firework.frontendmodel;

import senior.project.firework.models.Posting;
import senior.project.firework.models.Worker;

public class WhatFavorite {
    private long idWhatFavorite;
    private Worker worker;
    private Posting posting;

    public WhatFavorite(){

    }

    public WhatFavorite(long idWhatFavorite, Worker worker, Posting posting) {
        this.idWhatFavorite = idWhatFavorite;
        this.worker = worker;
        this.posting = posting;
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
}
