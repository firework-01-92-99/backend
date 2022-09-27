package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.models.Employer;
import senior.project.firework.repositories.repoRole;
import senior.project.firework.repositories.repoRatings;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.models.Ratings;
import senior.project.firework.models.Worker;
import senior.project.firework.models.Role;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    private repoRole repoRole;

    @GetMapping("/admin/getRatings")
    public List<Ratings> getRatings(){
        return repoRatings.findAll();
    }

    @GetMapping("/main/getDate")
    public LocalDate getDate(){
        LocalDate date = LocalDate.now();
        return date;
    }

    @PostMapping("/emp_worker/giveScore")
    public String GiveScore(@RequestParam(name = "idWorker") long idWorker,
                            @RequestParam(name = "idEmployer") long idEmployer,
                            @RequestParam(name = "rate") long rate,
                            @RequestParam(name = "comment") String comment,
                            @RequestParam(name = "idRole") long idRole){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Role role = repoRole.findById(idRole).orElse(null);
        if( repoRatings.findByWorkerAndEmployerAndForwho(worker, employer, role.getRoleName() ) !=null ){
            return "You give score already.";
        }
        LocalDate date = LocalDate.now();
        Ratings ratings = new Ratings(rate,comment,date, role.getRoleName(), employer,worker);
        repoRatings.save(ratings);
        return "Score = " + ratings.getRate();
    }

    @PutMapping("/emp_worker/editScore")
    public String EditScore(@RequestBody Ratings ratings){
        LocalDate date = LocalDate.now();
        ratings.setTimestamp(date);
        repoRatings.save(ratings);
        return "Score = " + ratings.getRate();
    }

    @DeleteMapping("/emp_worker/cancelScore")
    public void cancelScore(@RequestParam(name = "idRating") long idRating){
        repoRatings.deleteById(idRating);
    }

    @GetMapping("/emp_worker/getMyTotalScore")
    public Double getMyTotalScore(@RequestParam(name = "idWorkerOrEmployer") long idWorkerOrEmployer,@RequestParam(name = "idRole") long idRole){
        Role role = repoRole.findById(idRole).orElse(null);
        if(role.getRoleName().equals("ROLE_WORKER")){
            Worker worker = repoWorker.getById(idWorkerOrEmployer);
            List<Ratings> ratingsList = repoRatings.findByWorkerAndForwho(worker,role.getRoleName());
            return returnScoreDouble(ratingsList);
        }else if(role.getRoleName().equals("ROLE_EMP")){
            Employer employer = repoEmployer.getById(idWorkerOrEmployer);
            List<Ratings> ratingsList = repoRatings.findByEmployerAndForwho(employer,role.getRoleName());
            return returnScoreDouble(ratingsList);
        }
        return null;
    }

    public Double returnScoreDouble(List<Ratings> ratingsList){
        double totalScore = 0;
        double count = 0;
        for (Ratings ratingPerLine : ratingsList) {
            totalScore += ratingPerLine.getRate();
            count++;
        }
        double realScore = totalScore/count;
        return realScore;
    }

    @GetMapping("/emp_worker/getMyScoreList")
    public List<Ratings> getMyScoreList(@RequestParam(name = "idWorkerOrEmployer") long idWorkerOrEmployer,@RequestParam(name = "idRole") long idRole){
        Role role = repoRole.findById(idRole).orElse(null);
        if(role.getRoleName().equals("ROLE_WORKER")){
            Worker worker = repoWorker.getById(idWorkerOrEmployer);
            List<Ratings> ratingsList = repoRatings.findByWorkerAndForwho(worker,role.getRoleName());
            return ratingsList;
        }else if(role.getRoleName().equals("ROLE_EMP")){
            Employer employer = repoEmployer.getById(idWorkerOrEmployer);
            List<Ratings> ratingsList = repoRatings.findByEmployerAndForwho(employer,role.getRoleName());
            return ratingsList;
        }
        return null;
    }
}
