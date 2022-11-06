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
import senior.project.firework.frontendmodel.MyRatingsWithEmployerOrWorker;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @GetMapping("/emp/getEmpTotalScore")
    public Double getEmpTotalScore(@RequestParam(name = "idEmployer") long idEmployer){
        Employer employer = repoEmployer.getById(idEmployer);
        List<Ratings> ratingsList = repoRatings.findByEmployerAndForwho(employer,employer.getAccount().getRole().getRoleName());
        return returnScoreDouble(ratingsList);
    }

    @GetMapping("/worker/getWorkerTotalScore")
    public Double getWorkerTotalScore(@RequestParam(name = "idWorker") long idWorker){
        Worker worker = repoWorker.getById(idWorker);
        List<Ratings> ratingsList = repoRatings.findByWorkerAndForwho(worker,worker.getAccount().getRole().getRoleName());
        return returnScoreDouble(ratingsList);
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

    @GetMapping("/emp/getEmployerScoreList")
    public List<MyRatingsWithEmployerOrWorker> getEmployerScoreList(@RequestParam(name = "idEmployer") long idEmployer){
        Employer employer = repoEmployer.getById(idEmployer);
        List<Ratings> ratingsList = repoRatings.findByEmployerAndForwho(employer,employer.getAccount().getRole().getRoleName());
        List<MyRatingsWithEmployerOrWorker> myRatingsWithEmployerOrWorkerList = new ArrayList<>();
        for(Ratings ratingPerLine:ratingsList){
            MyRatingsWithEmployerOrWorker new1 = new MyRatingsWithEmployerOrWorker(ratingPerLine.getIdRating(),
                    ratingPerLine.getRate(),ratingPerLine.getComment(),ratingPerLine.getTimestamp(),
                    ratingPerLine.getWorker().getFirstName()+" "+ratingPerLine.getWorker().getLastName());
            myRatingsWithEmployerOrWorkerList.add(new1);
        }
        return myRatingsWithEmployerOrWorkerList;
    }

    @GetMapping("/worker/getWorkerScoreList")
    public List<MyRatingsWithEmployerOrWorker> getWorkerScoreList(@RequestParam(name = "idWorker") long idWorker){
        Worker worker = repoWorker.getById(idWorker);
        List<Ratings> ratingsList = repoRatings.findByWorkerAndForwho(worker,worker.getAccount().getRole().getRoleName());
        List<MyRatingsWithEmployerOrWorker> myRatingsWithEmployerOrWorkerList = new ArrayList<>();
        for(Ratings ratingPerLine:ratingsList){
            MyRatingsWithEmployerOrWorker new1 = new MyRatingsWithEmployerOrWorker(ratingPerLine.getIdRating(),
                    ratingPerLine.getRate(),ratingPerLine.getComment(),ratingPerLine.getTimestamp(),
                    ratingPerLine.getEmployer().getEstablishmentName());
            myRatingsWithEmployerOrWorkerList.add(new1);
        }
        return myRatingsWithEmployerOrWorkerList;
    }
}
