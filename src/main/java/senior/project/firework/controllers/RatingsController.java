package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.repositories.repoRatings;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.models.Ratings;

import java.util.Date;

@RestController
public class RatingsController {
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoRatings repoRatings;

    @PostMapping("/emp/giveScore")
    public Ratings GiveScore(@RequestBody Ratings ratings){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        ratings.setTimestamp((java.sql.Date) sqlDate);
        return repoRatings.save(ratings);
    }

    @PutMapping("/emp/editScore")
    public Ratings EditScore(@RequestBody Ratings ratings){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        ratings.setTimestamp((java.sql.Date) sqlDate);
        return repoRatings.save(ratings);
    }

    @GetMapping("/worker/getMyScore")
    public long getMyScore(@RequestParam(name = "idWorker") long idworker){

        return 0;
    }
}
