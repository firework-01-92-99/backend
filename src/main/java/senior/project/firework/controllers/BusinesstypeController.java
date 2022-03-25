package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Businesstype;
import senior.project.firework.repositories.repoBusinesstype;

import java.util.List;
import java.util.Optional;

@RestController
public class BusinesstypeController {
    @Autowired
    private repoBusinesstype repoBusinesstype;

    @GetMapping("/allBusinesstype")
    public List<Businesstype> allBusinesstype(){
        return repoBusinesstype.findAll();
    }

    @GetMapping("/selectBusinesstype")
    public Optional<Businesstype> selectBusinesstype(@RequestParam(name = "idBusinessType") long idBusinessType){
        return repoBusinesstype.findById(idBusinessType);
    }
}
