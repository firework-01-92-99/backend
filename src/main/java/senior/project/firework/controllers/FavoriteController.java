package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.frontendmodel.WhatFavorite;
import senior.project.firework.models.Favorite;
import senior.project.firework.models.Posting;
import senior.project.firework.models.Worker;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoFavorite;
import senior.project.firework.repositories.repoPosting;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoFavorite repoFavorite;
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/admin/allFavorite")
    public List<WhatFavorite> allFavorite(){
        List<WhatFavorite> whatFavoriteList = new ArrayList<WhatFavorite>();
        List<Favorite> favoriteList = repoFavorite.findAll();
        for(Favorite favoritePerLine : favoriteList){
            WhatFavorite whatFavorite = new WhatFavorite(favoritePerLine.getIdFavorite(),
                    favoritePerLine.getWorker(),favoritePerLine.getPosting());
            whatFavoriteList.add(whatFavorite);
        }
        return whatFavoriteList;
    }

    @GetMapping("/worker/getMyFavorite")
    public List<WhatFavorite> getMyFavorite(@RequestParam(name = "idWorker") long idWorker){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        List<WhatFavorite> whatFavoriteList = new ArrayList<WhatFavorite>();
        List<Favorite> favoriteList = repoFavorite.findByWorker(worker);
        for(Favorite favoritePerLine : favoriteList){
            WhatFavorite whatFavorite = new WhatFavorite(favoritePerLine.getIdFavorite(),
                    favoritePerLine.getWorker(),favoritePerLine.getPosting());
            whatFavoriteList.add(whatFavorite);
        }
        return whatFavoriteList;
    }

    @PostMapping("/worker/addMyFavorite")
    public WhatFavorite addMyFavorite(@RequestParam(name = "idWorker") long idWorker,
                                      @RequestParam(name = "idPosting") long idPosting){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        Favorite favorite = new Favorite(worker,posting);
        repoFavorite.save(favorite);
        WhatFavorite whatFavorite = new WhatFavorite(favorite.getIdFavorite(), favorite.getWorker(), favorite.getPosting());
        return whatFavorite;
    }

    @DeleteMapping("/worker/deleteMyFavorite")
    public void deleteMyFavorite(@RequestParam(name = "idFavorite") long idFavorite){
        Favorite favorite = repoFavorite.findById(idFavorite).orElse(null);
        repoFavorite.delete(favorite);
    }

}
