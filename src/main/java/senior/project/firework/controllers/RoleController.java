package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Role;
import senior.project.firework.repositories.repoRole;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    private repoRole repoRole;

    @GetMapping("/allRole")
    public List<Role> allRole(){
        return repoRole.findAll();
    }

    @GetMapping("/selectRole")
    public Optional<Role> selectRole(@RequestParam(name = "idRole") long idRole){
        return repoRole.findById(idRole);
    }
}
