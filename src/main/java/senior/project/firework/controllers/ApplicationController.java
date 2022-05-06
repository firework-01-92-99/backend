package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoApplication;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoStatus;

import java.util.List;
import java.util.Optional;

@RestController
public class ApplicationController {

    @Autowired
    private repoApplication repoApplication;
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoPosting repoPosting;
    @Autowired
    private repoStatus repoStatus;

    @GetMapping("/admin/allApplication")
    public List<Application> allApplication(){
        return repoApplication.findAll();
    }

    @PostMapping("/worker/workApp")
    public Application workerApplication(@RequestParam(value = "idWorker") long idWorker,
                                         @RequestParam(value = "idPosting") long idPosting){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Posting post = repoPosting.findById(idPosting).orElse(null);
        Status status = repoStatus.findById(3L).orElse(null);
        Application newApplication = new Application(worker,post,status);
        return repoApplication.save(newApplication);
    }

    @DeleteMapping("/worker/workCancelApp")
    public String workerCancelApplication(@RequestParam(value = "idApplication") long idApplication){
        Application delApplication = repoApplication.findById(idApplication).orElse(null);
        repoApplication.delete(delApplication);
        return "Cancel Success!";
    }

    @GetMapping("/admin/selectApplication")
    public Optional<Application> selectApplication(@RequestParam(name = "idApplication") long idApplication){
        return repoApplication.findById(idApplication);
    }

    @GetMapping("/admin/selectApplicationByWorker")
    public List<Application> selectApplicationByWorker(@RequestParam(name = "idWorker") String idWorker){
        return repoApplication.selectByIdworker(idWorker);
    }

}
