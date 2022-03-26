package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Posting;
import senior.project.firework.repositories.repoPosting;

import java.util.List;

@RestController
public class PostingController {
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/allPosting")
    public List<Posting> allPosting(){
        return repoPosting.findAll();
    }

    @GetMapping("/searchPosting")
    public List<Posting> searchPosting(@RequestParam(name = "establishmentName") String establishmentName,
                                       @RequestParam(name = "positionName") String positionName,
                                       @RequestParam(name = "idHiringtype") String idHiringtype,
                                       @RequestParam(name = "sortSalary")  String sortSalary,// DESC = High to Low - ASC = Low to High
                                       @RequestParam(name = "idProvince") String idProvince,
                                       @RequestParam(name = "idDistrict") String idDistrict,
                                       @RequestParam(name = "idSubdistrict") String idSubdistrict){
        return repoPosting.searchPosting(establishmentName,positionName,idHiringtype,idProvince,idDistrict,idSubdistrict,sortSalary);
    }
}
