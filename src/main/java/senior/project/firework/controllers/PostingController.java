package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoPostingHasDay;
import senior.project.firework.repositories.repoPosition;
import senior.project.firework.repositories.repoPostingOpenClose;
import senior.project.firework.repositories.repoApplication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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
    @Autowired
    private repoPostingHasDay repoPostingHasDay;
    @Autowired
    private repoPosition repoPosition;
    @Autowired
    private repoPostingOpenClose repoPostingOpenClose;
    @Autowired
    private repoApplication repoApplication;

    @GetMapping("/main/posting")
    public List<Posting> allPosting(){
        return repoPosting.findAll();
    }

    @GetMapping("/main/allPosting")
    public Page<Posting> allPosting(@RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo,@RequestParam(defaultValue = "3",name = "size") Integer size){
        Pageable pageable = PageRequest.of(pageNo,size);
        return repoPosting.findAllActive(pageable);
    }

    @GetMapping("/main/searchPosting")
    public Page<Posting> searchPosting(@RequestParam(defaultValue = "" , name = "establishmentAndpositionName") String establishmentAndpositionName,
                                       @RequestParam(defaultValue = "" , name = "idHiringtype") String idHiringtype,
                                       @RequestParam(defaultValue = "" , name = "sortSalary")  String sortSalary,// DESC = High to Low - ASC = Low to High
                                       @RequestParam(defaultValue = "" , name = "idProvince") String idProvince,
                                       @RequestParam(defaultValue = "0" , name = "minSalary") long minSalary,
                                       @RequestParam(defaultValue = "100000" , name = "maxSalary") long maxSalary,
                                       @RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo,
                                       @RequestParam(defaultValue = "3",name = "size") Integer size){
        Pageable pageable = PageRequest.of(pageNo,size);
        if(minSalary > maxSalary){
            long newMinSalary = maxSalary;
            long newMaxSalary = minSalary;
            minSalary = newMinSalary;
            maxSalary = newMaxSalary;
        }else if(minSalary == maxSalary){
            maxSalary = 100000;
        }
        return repoPosting.searchPosting(establishmentAndpositionName,idHiringtype,sortSalary,idProvince,minSalary,maxSalary,pageable);
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
        ZonedDateTime date = ZonedDateTime.now();
        Posting newPosting = new Posting(posting.getSex(),posting.getWorkDescription(),posting.getMinAge(),posting.getMaxAge(),
                posting.getMinSalary(),posting.getMaxSalary(),posting.getOvertimePayment(),posting.getStartTime(),posting.getEndTime(),
                posting.getProperties(),posting.getWelfare(),date,posting.getHiringType(),employer,status,
                posting.getWorkerType(),posting.getPosition());

        repoPosting.save(newPosting);
        List<PostingHasDay> postingHasDayList = posting.getPostingHasDayList();
        for(PostingHasDay postingHasDayPerLine:postingHasDayList){
            PostingHasDay newPostingHasDay = new PostingHasDay(postingHasDayPerLine.getDay(),newPosting);
            repoPostingHasDay.save(newPostingHasDay);
        }
        PostingOpenClose newPostingOpenClose = new PostingOpenClose(date,1,newPosting);
        repoPostingOpenClose.save(newPostingOpenClose);
        return newPosting;
    }

    /*@PutMapping("/emp/editPosting")
    public Posting editPosting(@RequestBody Posting NewPosting){
        Posting OldPosting = repoPosting.findById(NewPosting.getIdPosting()).orElse(null);
        Posting NewPosting1 = repoPosting.findById(NewPosting.getIdPosting()).orElse(null);
        Employer employer = repoEmployer.getById(NewPosting1.getIdEmployer());
        NewPosting.setEmployer(employer);

        List<PostingHasDay> postingHasDayListOld = OldPosting.getPostingHasDayList();
        for(PostingHasDay postingHasDayPerLine:postingHasDayListOld){
            repoPostingHasDay.delete(postingHasDayPerLine);
        }
        List<PostingHasDay> postingHasDayListNew = NewPosting.getPostingHasDayList();
        for(PostingHasDay postingHasDayPerLine:postingHasDayListNew){
            PostingHasDay newPostingHasDay = new PostingHasDay(postingHasDayPerLine.getDay(),NewPosting);
            repoPostingHasDay.save(newPostingHasDay);
        }

        repoPosting.save(NewPosting);

        return NewPosting;

    }*/

    @PutMapping("/emp/ActivePosting")
    public void ActivePosting(@RequestParam(name = "idPosting") long idPosting){
        Status status = repoStatus.findById(1L).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        posting.setStatus(status);
        repoPosting.save(posting);

        ZonedDateTime date = ZonedDateTime.now();
        long maxIdPostingOpenClose = repoPostingOpenClose.getMAXIdPostingOpenCloseByPosting(posting);
        PostingOpenClose postingOpenClose = repoPostingOpenClose.findById(maxIdPostingOpenClose).orElse(null);
        PostingOpenClose newPostingOpenClose = new PostingOpenClose(date,postingOpenClose.getRound()+1,posting);
        repoPostingOpenClose.save(newPostingOpenClose);
    }

    @PutMapping("/emp/inActivePosting")
    public void inActivePosting(@RequestParam(name = "idPosting") long idPosting){
        Status status = repoStatus.findById(2L).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        posting.setStatus(status);
        repoPosting.save(posting);

        long maxIdPostingOpenClose = repoPostingOpenClose.getMAXIdPostingOpenCloseByPosting(posting);
        PostingOpenClose postingOpenClose = repoPostingOpenClose.findById(maxIdPostingOpenClose).orElse(null);
        ZonedDateTime date = ZonedDateTime.now();
        postingOpenClose.setInactiveDate(date);
        repoPostingOpenClose.save(postingOpenClose);
    }

    @PutMapping("/emp/deletePosting")
    public void deletePosting(@RequestParam(name = "idPosting") long idPosting) throws Exception {
        Status status = repoStatus.findById(9L).orElse(null);
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        posting.setStatus(status);
        if(!posting.getApplicationList().isEmpty()){
            List<Application> applicationList = repoApplication.findByPosting(posting);
            for(Application applicationPerLine:applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == 13 ||
                        applicationPerLine.getStatus().getIdStatus() == 20 ){
                    throw new AccountException(ExceptionRepo.ERROR_CODE.POSTING_APPLICATION_NOT_FINISH,"All application not Done or RejectOnWeb");
                }
            }
            repoPosting.save(posting);
        }else{
            repoPosting.save(posting);
        }
    }

    @GetMapping("/main/getMaxRoundOfPosting")
    public long getMaxRoundOfPosting(@RequestParam(name = "idPosting") long idPosting){
        long maxIdPostingOpenClose = repoPostingOpenClose.getMAXIdPostingOpenCloseByPosting(repoPosting.findById(idPosting).orElse(null));
        PostingOpenClose postingOpenClose = repoPostingOpenClose.findById(maxIdPostingOpenClose).orElse(null);
        return postingOpenClose.getRound();
    }

    @GetMapping("/main/getPostingActiveByIdEmployer")
    public Page<Posting> getPostingActiveByIdEmployer(@RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo,
                                                      @RequestParam(name = "idEmployer") long idEmployer,
                                                      @RequestParam(defaultValue = "3",name = "size") Integer size){
        Pageable pageable = PageRequest.of(pageNo,size);
        return repoPosting.findAllActiveByEmployerId(idEmployer,pageable);
    }

    @GetMapping("/main/getPostingInActiveByIdEmployer")
    public Page<Posting> getPostingInActiveByIdEmployer(@RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo,
                                                        @RequestParam(name = "idEmployer") long idEmployer){
        Pageable pageable = PageRequest.of(pageNo,3);
        return repoPosting.findAllInActiveByEmployerId(idEmployer,pageable);
    }

    @GetMapping("/main/getAllPostingByIdEmployer")
    public Page<Posting> getAllPostingByIdEmployer(@RequestParam(defaultValue = "0",name = "pageNo") Integer pageNo,
                                                   @RequestParam(name = "idEmployer") long idEmployer){
        Pageable pageable = PageRequest.of(pageNo,3);
        return repoPosting.findAllPostingByEmployerId(idEmployer,pageable);
    }

}