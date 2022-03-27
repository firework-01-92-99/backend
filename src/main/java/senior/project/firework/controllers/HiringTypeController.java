package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.HiringType;
import senior.project.firework.repositories.repoHiringType;

import java.util.List;
import java.util.Optional;

@RestController
public class HiringTypeController {
    @Autowired
    private repoHiringType repoHiringType;

    @GetMapping("/main/allHiringType")
    public List<HiringType> allHiringType(){
        return repoHiringType.findAll();
    }

    @GetMapping("/main/selectHiringType")
    public Optional<HiringType> selectHiringType(@RequestParam(name = "idHiringtype") long idHiringtype){
        return repoHiringType.findById(idHiringtype);
    }
}
