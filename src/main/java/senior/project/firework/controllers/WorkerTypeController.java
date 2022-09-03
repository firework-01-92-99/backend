package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.WorkerType;
import senior.project.firework.repositories.repoWorkerType;;import java.util.List;

@RestController
public class WorkerTypeController {
    @Autowired
    private repoWorkerType repoWorkerType;

    @GetMapping("/main/allWorkerType")
    public List<WorkerType> allWorkerType(){
        return repoWorkerType.findAll();
    }

    @GetMapping("/main/selectWorkerType")
    public WorkerType selectWorkerType(@RequestParam(name = "idWorkerType") long idWorkerType){
        return repoWorkerType.findById(idWorkerType).orElse(null);
    }

}
