package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Employer;
import senior.project.firework.repositories.repoEmployer;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployerController {
    @Autowired
    private repoEmployer repoEmployer;

    @GetMapping("/allEmployer")
    public List<Employer> allEmployer(){
        return repoEmployer.findAll();
    }

    @GetMapping("/selectEmployer")
    public Optional<Employer> selectEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        return repoEmployer.findById(idEmployer);
    }
}
