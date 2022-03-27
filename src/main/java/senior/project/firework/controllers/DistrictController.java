package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.District;
import senior.project.firework.repositories.repoDistrict;

import java.util.List;
import java.util.Optional;

@RestController
public class DistrictController {
    @Autowired
    private repoDistrict repoDistrict;

    @GetMapping("/main/allDistrict")
    public List<District> allDistrict(){
        return repoDistrict.findAll();
    }

    @GetMapping("/main/selectDistrict")
    public Optional<District> selectDistrict(@RequestParam(name = "idDistrict") String idDistrict){
        return repoDistrict.findById(idDistrict);
    }
}
