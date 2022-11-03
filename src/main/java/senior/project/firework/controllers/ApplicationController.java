package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.frontendmodel.StatusApplication;
import senior.project.firework.frontendmodel.HowManyApplication;
import senior.project.firework.frontendmodel.WhoApplication;
import senior.project.firework.frontendmodel.PoNameAndEstName;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoApplication;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoApplicationHasComment;
import senior.project.firework.repositories.repoAdmin;
import senior.project.firework.repositories.repoActToRegister;
import senior.project.firework.repositories.repoRole;
import senior.project.firework.repositories.repoRatings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ApplicationController {

    @Autowired
    private repoApplication repoApplication;
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoPosting repoPosting;
    @Autowired
    private repoStatus repoStatus;
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoApplicationHasComment repoApplicationHasComment;
    @Autowired
    private repoAdmin repoAdmin;
    @Autowired
    private repoActToRegister repoActToRegister;
    @Autowired
    private repoRole repoRole;
    @Autowired
    private repoRatings repoRatings;

    @GetMapping("/admin_worker/getPositionNameAndEstablishmentNameByIdApplication")
    public PoNameAndEstName getPositionNameAndEstablishmentNameByIdApplication(@RequestParam(name = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        PoNameAndEstName poNameAndEstName = new PoNameAndEstName(idApplication,application.getPosting().getPosition().getPositionName(),application.getPosting().getEmployer().getEstablishmentName());
        return poNameAndEstName;
    }

    @GetMapping("/admin/allApplication")
    public List<Application> allApplication(){
        return repoApplication.findAll();
    }

    @PostMapping("/worker/workApp")
    public Application workerApplication(@RequestParam(value = "idWorker") long idWorker,
                                         @RequestParam(value = "idPosting") long idPosting){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Posting post = repoPosting.findById(idPosting).orElse(null);
        Status status = repoStatus.findById(11L).orElse(null);
        Application newApplication = new Application(worker,post,status);
        newApplication.setIdStatusAdmin(0L);
        return repoApplication.save(newApplication);
    }

    @DeleteMapping("/worker/workCancelApp")
    public String workerCancelApplication(@RequestParam(value = "idApplication") long idApplication)throws Exception{
        Application delApplication = repoApplication.findById(idApplication).orElse(null);
        if(delApplication.getStatus().getIdStatus() != 11){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_EMPLOYER_ON_WEB,"You can't cancel , application in process!");
        }
        repoApplication.delete(delApplication);
        return "Cancel Success!";
    }

    public StatusApplication showStatusApplication(long idApplication){
        Optional<Application> application = repoApplication.findById(idApplication);
        Optional<Posting> posting = repoPosting.findById(application.get().getIdPosting());
        Optional<Employer> employer = repoEmployer.findById(posting.get().getIdEmployer());
        StatusApplication statusApplication = new StatusApplication(application.get().getIdApplication(),
                employer.get().getEstablishmentName(),
                posting.get().getPosition().getPositionName(),
                employer.get().getAddress(),
                employer.get().getProvince().getProvinceName(),
                employer.get().getDistrict().getDistrictName(),
                employer.get().getSubDistrict().getSubDistrict(),
                employer.get().getSubDistrict().getPostcode(),
                application.get().getStatus().getStatusName());
        return statusApplication;
    }

    @GetMapping("/admin_worker/selectApplication")
    public StatusApplication selectApplication(@RequestParam(name = "idApplication") long idApplication){
        return showStatusApplication(idApplication);
    }

    @GetMapping("/admin_worker/selectApplicationByWorker")
    public List<StatusApplication> selectApplicationByWorker(@RequestParam(name = "idWorker") long idWorker){
        List<StatusApplication> statusApplicationList = new ArrayList<>();
        Optional<Worker> worker = repoWorker.findById(idWorker);
        List<Application> applicationList = worker.get().getApplicationList();
        for (Application applicationPerLine : applicationList){
            StatusApplication statusApplication = showStatusApplication(applicationPerLine.getIdApplication());
            statusApplicationList.add(statusApplication);
        }
        return statusApplicationList;
    }

    @GetMapping("/emp/showAllWorker")
    public HowManyApplication showAllWorker(@RequestParam(name = "idPosting") long idPosting,@RequestParam(name = "idStatus") long idStatus){
        return setAllWorker(idPosting,idStatus);
    }

    public HowManyApplication setAllWorker(long idPosting,long idStatus){
        List<WhoApplication> whoApplicationList = new ArrayList<>();
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        List<Application> applicationList = posting.getApplicationList();
        long count = 1;
        if(idStatus == 0){
            for(Application applicationPerLine : applicationList){
                count = getCount(whoApplicationList, count, applicationPerLine);
            }
        }else if(idStatus == 11){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }else if(idStatus == 12){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }else if(idStatus == 13){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }else if(idStatus == 14){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }else if(idStatus == 21){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }else if(idStatus == 24){
            for(Application applicationPerLine : applicationList){
                if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                    count = getCount(whoApplicationList, count, applicationPerLine);
                }
            }
        }
        HowManyApplication howManyApplication = new HowManyApplication(idPosting,whoApplicationList);
        return howManyApplication;
    }

    public long getCount(List<WhoApplication> whoApplicationList, long count, Application applicationPerLine) {
        String application_has_comment;
        if(applicationPerLine.getApplicationHasComment() == null){
            application_has_comment = "";
        }else{
            application_has_comment = applicationPerLine.getApplicationHasComment().getDescriptionRejectOnWeb();//Edit sometime
        }
        Worker worker = repoWorker.findById(applicationPerLine.getIdWorker()).orElse(null);
        WhoApplication whoApplication = new WhoApplication(count,applicationPerLine.getIdApplication(),worker.getIdWorker(),worker.getIdentificationNumber(),
                worker.getVerifyPic(),worker.getSex(),worker.getFirstName(),worker.getMiddleName(),worker.getLastName(),
                worker.getPhone(),worker.getWorkerType(),worker.getNationality(),applicationPerLine.getStatus().getStatusName(),application_has_comment);
        whoApplicationList.add(whoApplication);
        count++;
        return count;
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/emp/employerAcceptOnWeb")//Employer--------------------------------------------- Done
    public Application employerAcceptOnWeb(@RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(14L).orElse(null);//Wating_EmployerSummary
        application.setStatus(status);
        return repoApplication.save(application);
    }

    @PutMapping("/emp/employerRejectOnWeb")//Employer--------------------------------------------- Done
    public Application employerRejectOnWeb(@RequestBody ApplicationHasComment applicationHasComment,
                                           @RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(13L).orElse(null);//Reject_EmployerOnWeb
        application.setStatus(status);
        ApplicationHasComment newApplicationHasComment = new ApplicationHasComment(application);
        newApplicationHasComment.setDescriptionRejectOnWeb(applicationHasComment.getDescriptionRejectOnWeb());
        repoApplicationHasComment.save(newApplicationHasComment);
        application.setApplicationHasComment(newApplicationHasComment);
        return repoApplication.save(application);
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/emp/employerAcceptOnSite")//Employer--------------------------------------------- Done
    public Application employerAcceptOnSite(@RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(21L).orElse(null);//Wating_WorkerFinishJob
        application.setIdStatusAdmin(17L);//Wating_AdminSent
        application.setStatus(status);
        return repoApplication.save(application);
    }

    @PutMapping("/emp/employerRejectOnSite")//Employer--------------------------------------------- Done
    public Application employerRejectOnSite(@RequestBody ApplicationHasComment applicationHasComment,
                                            @RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(20L).orElse(null);//Done
        application.setIdStatusAdmin(17L);//Wating_AdminSent
        application.setStatus(status);
        ApplicationHasComment newApplicationHasComment = new ApplicationHasComment(application);
        newApplicationHasComment.setDescriptionRejectOnSite(applicationHasComment.getDescriptionRejectOnSite());
        repoApplicationHasComment.save(newApplicationHasComment);
        application.setApplicationHasComment(newApplicationHasComment);
        return repoApplication.save(application);
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/emp/employerFinishJob")//Employer--------------------------------------------- Done
    public Application employerFinishJob(@RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(24L).orElse(null);//Waiting_Rating
        application.setStatus(status);
        return repoApplication.save(application);
    }

    @PutMapping("/emp/employerBreakShort")//Employer--------------------------------------------- Done
    public Application employerBreakShort(@RequestBody ApplicationHasComment applicationHasComment,
                                          @RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(24L).orElse(null);//Waiting_Rating
        application.setStatus(status);
        ApplicationHasComment newApplicationHasComment = new ApplicationHasComment(application);
        newApplicationHasComment.setDescriptionBreakShort(applicationHasComment.getDescriptionBreakShort());
        repoApplicationHasComment.save(newApplicationHasComment);
        application.setApplicationHasComment(newApplicationHasComment);
        return repoApplication.save(application);
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/admin/adminComfirm")//Admin--------------------------------------------- Done
    public Application adminComfirm(@RequestBody ActToRegister actToRegister,
                                    @RequestParam(value = "idApplication") long idApplication,
                                    @RequestParam(value = "idAdmin") long idAdmin){
        Admin admin = repoAdmin.findById(idAdmin).orElse(null);
        Application application = repoApplication.findById(idApplication).orElse(null);
        application.setIdStatusAdmin(18L);//Admin_Confirm
        if(actToRegister.getDescription() == null || actToRegister.getDescription() == ""){
            ActToRegister newActToRegister = new ActToRegister(actToRegister.getAct_name(),admin);
            repoActToRegister.save(newActToRegister);
            application.setActToRegister(newActToRegister);
        }else{
            ActToRegister newActToRegister = new ActToRegister(actToRegister.getAct_name(),actToRegister.getDescription(),admin);
            repoActToRegister.save(newActToRegister);
            application.setActToRegister(newActToRegister);
        }
        return repoApplication.save(application);
    }

    @PutMapping("/admin/adminCancel")//Admin--------------------------------------------- Done
    public Application adminCancel(@RequestBody ActToRegister actToRegister,
                                   @RequestParam(value = "idApplication") long idApplication,
                                   @RequestParam(value = "idAdmin") long idAdmin){
        Admin admin = repoAdmin.findById(idAdmin).orElse(null);
        Application application = repoApplication.findById(idApplication).orElse(null);
        application.setIdStatusAdmin(19L);//Admin_Cancel
        ActToRegister newActToRegister = new ActToRegister(actToRegister.getAct_name(),application.getApplicationHasComment().getDescriptionRejectOnSite(),admin);
        repoActToRegister.save(newActToRegister);
        application.setActToRegister(newActToRegister);
        return repoApplication.save(application);
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/emp/employerGiveScoreToWorker")//Employer--------------------------------------------- Done
    public String employerGiveScoreToWorker(@RequestParam(value = "idApplication") long idApplication,
                                            @RequestBody Ratings ratings){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Role roleEmployer = repoRole.findById(2L).orElse(null);//Employer
        Role roleWorker = repoRole.findById(3L).orElse(null);//Worker
        LocalDate date = LocalDate.now();
        Ratings newRating = new Ratings(ratings.getRate(),ratings.getComment(),date,roleWorker.getRoleName(),application.getPosting().getEmployer(),application.getWorker());
        if( repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName() ) !=null ){
            return "You give score already.";
        }
        repoRatings.save(newRating);
        if(repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName() ) !=null &&
                repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName() ) !=null){
            Status status = repoStatus.findById(20L).orElse(null);//Done
            application.setStatus(status);
            repoApplication.save(application);
        }else{
            Status status = repoStatus.findById(26L).orElse(null);//empRated
            application.setStatus(status);
            repoApplication.save(application);
        }
        return "Score = " + ratings.getRate();
    }

    @PutMapping("/worker/workerGiveScoreToEmployer")//Worker--------------------------------------------- Done
    public String workerGiveScoreToEmployer(@RequestParam(value = "idApplication") long idApplication,
                                            @RequestBody Ratings ratings){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Role roleEmployer = repoRole.findById(2L).orElse(null);//Employer
        Role roleWorker = repoRole.findById(3L).orElse(null);//Worker
        LocalDate date = LocalDate.now();
        Ratings newRating = new Ratings(ratings.getRate(),ratings.getComment(),date,roleEmployer.getRoleName(),application.getPosting().getEmployer(),application.getWorker());
        if( repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName() ) !=null ){
            return "You give score already.";
        }
        repoRatings.save(newRating);
        if(repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName() ) !=null &&
                repoRatings.findByWorkerAndEmployerAndForwho(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName() ) !=null){
            Status status = repoStatus.findById(20L).orElse(null);//Done
            application.setStatus(status);
            repoApplication.save(application);
        }else{
            Status status = repoStatus.findById(25L).orElse(null);//workerRated
            application.setStatus(status);
            repoApplication.save(application);
        }
        return "Score = " + ratings.getRate();
    }
    //-------------------------------------------------------------------------------------------------
}
