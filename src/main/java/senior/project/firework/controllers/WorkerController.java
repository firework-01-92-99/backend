package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Worker;
import senior.project.firework.repositories.repoWorker;

import java.util.List;
import java.util.Optional;

@RestController
public class WorkerController {
    @Autowired
    private repoWorker repoWorker;

    @GetMapping("/admin/allWorker")
    public List<Worker> allWorker(){
        return repoWorker.findAll();
    }

    @GetMapping("/admin/selectWorker")
    public Optional<Worker> selectWorker(@RequestParam(name = "idWorker") long idWorker){
        return repoWorker.findById(idWorker);
    }
}
