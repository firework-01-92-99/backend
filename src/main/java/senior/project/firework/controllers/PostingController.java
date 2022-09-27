package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.Account;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Posting;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
public class PostingController {
    @Autowired
    private repoPosting repoPosting;
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoStatus repoStatus;

    @GetMapping("/main/posting")
    public List<Posting> allPosting(){
        return repoPosting.findAll();
    }

    @GetMapping("/main/allPosting")
    public Page<Posting> allPosting(@RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo,3);
        return repoPosting.findAllActive(pageable);
    }

    @GetMapping("/main/searchPosting")
    public Page<Posting> searchPosting(@RequestParam(defaultValue = "" , name = "establishmentAndpositionName") String establishmentAndpositionName,
                                       @RequestParam(defaultValue = "" , name = "idHiringtype") String idHiringtype,
                                       @RequestParam(defaultValue = "" , name = "sortSalary")  String sortSalary,// DESC = High to Low - ASC = Low to High
                                       @RequestParam(defaultValue = "" , name = "idProvince") String idProvince,
                                       @RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo,3);
        return repoPosting.searchPosting(establishmentAndpositionName,idHiringtype,sortSalary,idProvince,pageable);
    }

    @GetMapping("/main/selectPosting")
    public Optional<Posting> selectPosting(@RequestParam(name = "idPosting") long idPosting){
        return repoPosting.findById(idPosting);
    }

    @GetMapping("/main/selectEmployerFromPosting")
    public Employer selectEmployerFromPosting(@RequestParam(name = "idPosting")long idPosting){
        return repoPosting.selectEmployerByPostingId(idPosting);
    }

    @PostMapping("/emp/createPosting")
    public Posting createPosting(@RequestBody Posting posting,@RequestParam(name = "idEmployer") long idEmployer){
        Employer employer = repoEmployer.getById(idEmployer);
        Status status = repoStatus.findById(1L).orElse(null);
        Posting newPosting = new Posting(posting.getSex(),posting.getWorkDescription(),posting.getMinAge(),posting.getMaxAge(),
                posting.getMinSalary(),posting.getMaxSalary(),posting.getOvertimePayment(),posting.getStartTime(),posting.getEndTime(),
                posting.getProperties(),posting.getWelfare(),posting.getHiringType(),employer,status,
                posting.getWorkerType(),posting.getPostingHasDayList(),posting.getPosition());
        return repoPosting.save(newPosting);
    }

    @PutMapping("/emp/editPosting")
    public Posting editPosting(@RequestBody Posting posting){
        return repoPosting.save(posting);
    }

    @PutMapping("/emp/ActivePosting")
    public void ActivePosting(@RequestParam(name = "idPosting") long idPosting){
        Status status = repoStatus.findById(1L).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        posting.setStatus(status);
        repoPosting.save(posting);
    }

    @PutMapping("/emp/inActivePosting")
    public void inActivePosting(@RequestParam(name = "idPosting") long idPosting){
        Status status = repoStatus.findById(2L).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        posting.setStatus(status);
        repoPosting.save(posting);
    }


}
