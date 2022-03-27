package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Province;
import senior.project.firework.repositories.repoProvince;

import java.util.List;
import java.util.Optional;

@RestController
public class ProvinceController {
    @Autowired
    private repoProvince repoProvince;

    @GetMapping("/main/allProvince")
    public List<Province> allProvince(){
        return repoProvince.findAll();
    }

    @GetMapping("/main/selectProvince")
    public Optional<Province> selectProvince(@RequestParam(name = "idProvince") String idProvince){
        return repoProvince.findById(idProvince);
    }
}
