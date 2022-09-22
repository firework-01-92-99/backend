package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Worker;
import senior.project.firework.repositories.repoWorker;

import java.util.List;

@RestController
public class WorkerController {
    @Autowired
    private repoWorker repoWorker;

    @GetMapping("/main/allWorker")
    public List<Worker> allWorker(){
        return repoWorker.findAll();
    }

    @GetMapping("/admin/selectWorker")
    public Worker selectWorker(@RequestParam(name = "idWorker") long idWorker){
        return repoWorker.findById(idWorker).orElse(null);
    }
}
