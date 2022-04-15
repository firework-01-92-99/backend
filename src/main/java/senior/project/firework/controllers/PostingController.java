package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Posting;
import senior.project.firework.repositories.repoPosting;

import java.util.List;
import java.util.Optional;

@RestController
public class PostingController {
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/main/allPosting")
    public List<Posting> allPosting(){
        return repoPosting.findAll();
    }

    @GetMapping("/main/searchPosting")
    public List<Posting> searchPosting(@RequestParam(defaultValue = "" , name = "establishmentName") String establishmentName,
                                       @RequestParam(defaultValue = "" , name = "positionName") String positionName,
                                       @RequestParam(defaultValue = "" , name = "idHiringtype") String idHiringtype,
                                       @RequestParam(defaultValue = "" , name = "sortSalary")  String sortSalary,// DESC = High to Low - ASC = Low to High
                                       @RequestParam(defaultValue = "" , name = "idProvince") String idProvince,
                                       @RequestParam(defaultValue = "" , name = "idDistrict") String idDistrict,
                                       @RequestParam(defaultValue = "" , name = "idSubdistrict") String idSubdistrict){
        return repoPosting.searchPosting(establishmentName,positionName,idHiringtype,idProvince,idDistrict,idSubdistrict,sortSalary);
    }

    @GetMapping("/main/selectPosting")
    public Optional<Posting> selectPosting(@RequestParam(name = "idPosting") long idPosting){
        return repoPosting.findById(idPosting);
    }

    @GetMapping("/main/selectEmployerFromPosting")
    public Employer selectEmployerFromPosting(@RequestParam(name = "idPosting")long idPosting){
        return repoPosting.selectEmployerByPostingId(idPosting);
    }
}
