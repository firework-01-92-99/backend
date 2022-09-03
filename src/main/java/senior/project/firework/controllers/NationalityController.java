package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Nationality;
import senior.project.firework.repositories.repoNationality;

import java.util.List;

@RestController
public class NationalityController {
    @Autowired
    private repoNationality repoNationality;

    @GetMapping("/main/allNationality")
    public List<Nationality> allNationality(){
        return repoNationality.findAll();
    }

    @GetMapping("/main/selectNationality")
    public Nationality selectNationality(@RequestParam(name = "idNationality") long idNationality){
        return repoNationality.findById(idNationality).orElse(null);
    }

}
