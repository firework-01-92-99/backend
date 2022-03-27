package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.SubDistrict;
import senior.project.firework.repositories.repoSubDistrict;

import java.util.List;

@RestController
public class SubDistrictController {
    @Autowired
    private repoSubDistrict repoSubDistrict;

    @GetMapping("/main/allSubDistrict")
    public List<SubDistrict> allSubDistrict(){
        return repoSubDistrict.findAll();
    }
}
