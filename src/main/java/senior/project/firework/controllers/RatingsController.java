package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.repositories.repoRatings;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.models.Ratings;
import senior.project.firework.models.Worker;

import java.util.Date;
import java.util.List;

@RestController
public class RatingsController {
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoRatings repoRatings;

    @PostMapping("/emp/giveScore")
    public String GiveScore(@RequestBody Ratings ratings){
        if( ratings.getEmployer() == repoEmployer.getById(ratings.getEmployer().getIdEmployer())
                && ratings.getWorker() == repoWorker.getById(ratings.getWorker().getIdWorker() )){
            return "You give score already.";
        }
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        ratings.setTimestamp((java.sql.Date) sqlDate);
        repoRatings.save(ratings);
        return "Score = "+ratings.getRate();
    }

    @PutMapping("/emp/editScore")
    public String EditScore(@RequestBody Ratings ratings){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        ratings.setTimestamp((java.sql.Date) sqlDate);
        repoRatings.save(ratings);
        return "Score = "+ratings.getRate();
    }

    @DeleteMapping("/emp/cancelScore")
    public void cancelScore(@RequestParam(name = "idRating") long idRating){
        repoRatings.deleteById(idRating);
    }

    @GetMapping("/worker/getMyTotalScore")
    public double getMyTotalScore(@RequestParam(name = "idWorker") long idworker){
        Worker worker = repoWorker.getById(idworker);
        List<Ratings> ratingsList = repoRatings.findByWorker(worker);
        double totalScore = 0;
        double count = 0;
        for (Ratings ratingPerLine : ratingsList) {
            totalScore += ratingPerLine.getRate();
            count++;
        }
        double realScore = totalScore/count;
        return Math.floor(realScore * 100)/100;
    }

    @GetMapping("/worker/getMyScoreList")
    public List<Ratings> getMyScoreList(@RequestParam(name = "idWorker") long idworker){
        Worker worker = repoWorker.getById(idworker);
        List<Ratings> ratingsList = repoRatings.findByWorker(worker);
        return ratingsList;
    }
}
