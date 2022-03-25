package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Admin;
import senior.project.firework.repositories.repoAdmin;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private repoAdmin repoAdmin;

    @GetMapping("/allAdmin")
    public List<Admin> allAdmin(){
        return repoAdmin.findAll();
    }

    @GetMapping("/selectAdmin")
    public Optional<Admin> selectAdmin(@RequestParam(name = "idAdmin") long idAdmin){
        return repoAdmin.findById(idAdmin);
    }
}
