package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoStatus;

import java.util.List;
import java.util.Optional;

@RestController
public class StatusController {
    @Autowired
    private repoStatus repoStatus;

    @GetMapping("/allStatus")
    public List<Status> allStatus(){
        return repoStatus.findAll();
    }

    @GetMapping("/selectStatus")
    public Optional<Status> selectStatus(@RequestParam(name = "idStatus") long idStatus){
        return repoStatus.findById(idStatus);
    }
}
