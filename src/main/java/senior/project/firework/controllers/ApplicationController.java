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
import senior.project.firework.repositories.*;
import senior.project.firework.services.EmailBusiness;


import java.time.LocalDate;
import java.time.ZonedDateTime;
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
    @Autowired
    private repoPostingOpenClose repoPostingOpenClose;
    @Autowired
    private EmailBusiness emailBusiness;
    @Autowired
    private repoProvince repoProvince;
    @Autowired
    private repoDistrict repoDistrict;
    @Autowired
    private repoSubDistrict repoSubDistrict;

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

    @GetMapping("/admin/allApplicationByIdStatus")
    public List<StatusApplication> allApplicationByIdStatus(@RequestParam(name = "idStatus") long idStatus){
        List<StatusApplication> statusApplicationList = new ArrayList<>();
        Status status = repoStatus.findById(idStatus).orElse(null);
        List<Application> applicationList = repoApplication.findByStatus(status);
        for(Application applicationPerLine:applicationList){
            StatusApplication statusApplication = showStatusApplication(applicationPerLine.getIdApplication());
            statusApplicationList.add(statusApplication);
        }
        return statusApplicationList;
    }

    @PostMapping("/worker/workApp")
    public Application workerApplication(@RequestParam(value = "idWorker") long idWorker,
                                         @RequestParam(value = "idPosting") long idPosting){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Posting post = repoPosting.findById(idPosting).orElse(null);
        Status status = repoStatus.findById(11L).orElse(null);
        ZonedDateTime date = ZonedDateTime.now();
        long getMaxIdPostOpenClose = repoPostingOpenClose.getMAXIdPostingOpenCloseByPosting(post);
        PostingOpenClose postingOpenClose = repoPostingOpenClose.findById(getMaxIdPostOpenClose).orElse(null);
        Application newApplication = new Application(worker,post,status,date,postingOpenClose.getRound());
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
                application.get().getStatus().getStatusName(),
                application.get().getDate(),
                application.get().getActToRegister());
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
    public HowManyApplication showAllWorker(@RequestParam(name = "idPosting") long idPosting,
                                            @RequestParam(name = "idStatus") long idStatus){
        return setAllWorker(idPosting,idStatus,0);
    }

    @GetMapping("/admin_emp/showAllWorkerAllPostingByTwoStatusAndRound")
    public List<HowManyApplication> showAllWorkerAllPostingByTwoStatusAndRound(@RequestParam(name = "idStatus1") long idStatus1,
                                                                         @RequestParam(name = "idStatus2") long idStatus2,
                                                                         @RequestParam(name = "round",defaultValue = "1") long round){
        List<HowManyApplication> howManyApplicationList = new ArrayList<>();
        List<Posting> AllPosting = repoPosting.findAll();
        for(Posting postingPerLine:AllPosting){
                HowManyApplication howManyApplication1 = setAllWorker(postingPerLine.getIdPosting(),idStatus1,round);
                HowManyApplication howManyApplication2 = setAllWorker(postingPerLine.getIdPosting(),idStatus2,round);
                for(WhoApplication whoApplicationPerLine:howManyApplication2.getWhoApplicationList()){
                    howManyApplication1.getWhoApplicationList().add(whoApplicationPerLine);
                }
                if(!howManyApplication1.getWhoApplicationList().isEmpty() || !howManyApplication2.getWhoApplicationList().isEmpty()){
                    howManyApplicationList.add(howManyApplication1);
                }
        }
        return howManyApplicationList;
    }

    @GetMapping("/admin_emp/showAllWorkerByTwoStatusAndRound")
    public HowManyApplication showAllWorkerByTwoStatus(@RequestParam(name = "idPosting") long idPosting,
                                                                       @RequestParam(name = "idStatus1") long idStatus1,
                                                                       @RequestParam(name = "idStatus2",defaultValue = "0") long idStatus2,
                                                                       @RequestParam(name = "round",defaultValue = "1") long round){
        if(idStatus2 == 0){
            HowManyApplication howManyApplication = setAllWorker(idPosting,idStatus1,round);
            return howManyApplication;
        }else{
            HowManyApplication howManyApplication1 = setAllWorker(idPosting,idStatus1,round);
            HowManyApplication howManyApplication2 = setAllWorker(idPosting,idStatus2,round);
            for(WhoApplication whoApplicationPerLine:howManyApplication2.getWhoApplicationList()){
                howManyApplication1.getWhoApplicationList().add(whoApplicationPerLine);
            }
            return howManyApplication1;
        }
    }

    @GetMapping("/admin_emp/showAllWorkerByTwoAdminStatus")
    public List<HowManyApplication> showAllWorkerByTwoAdminStatus(@RequestParam(name = "idStatusAdmin1") long idStatusAdmin1,
                                                                  @RequestParam(name = "idStatusAdmin2",defaultValue = "0") long idStatusAdmin2,
                                                                  @RequestParam(name = "round",defaultValue = "1") long round){
        if(idStatusAdmin2 == 0){
            List<HowManyApplication> howManyApplicationList = new ArrayList<>();
            HowManyApplication howManyApplication;
            List<Posting> posting = repoPosting.findAll();
            for(Posting postingPerLine:posting){
                howManyApplication = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(),idStatusAdmin1,round);
                if(!howManyApplication.getWhoApplicationList().isEmpty()){
                    howManyApplicationList.add(howManyApplication);
                }
            }
            return howManyApplicationList;
        }else{
            List<HowManyApplication> howManyApplicationList = new ArrayList<>();
            HowManyApplication howManyApplication1;
            HowManyApplication howManyApplication2;
            List<Posting> posting = repoPosting.findAll();
            for(Posting postingPerLine:posting) {
                howManyApplication1 = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(), idStatusAdmin1,round);
                howManyApplication2 = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(), idStatusAdmin2,round);
                for(WhoApplication whoApplicationPerLine:howManyApplication2.getWhoApplicationList()){
                    howManyApplication1.getWhoApplicationList().add(whoApplicationPerLine);
                }
                if(!howManyApplication1.getWhoApplicationList().isEmpty()){
                    howManyApplicationList.add(howManyApplication1);
                }
            }
            return howManyApplicationList;
        }
    }

    @GetMapping("/admin_emp/showAllWorkerByTwoAdminStatusReturnEmployer")
    public List<Employer> showAllWorkerByTwoAdminStatusReturnEmployer(@RequestParam(name = "idStatusAdmin1") long idStatusAdmin1,
                                                                  @RequestParam(name = "idStatusAdmin2",defaultValue = "0") long idStatusAdmin2,
                                                                  @RequestParam(name = "round",defaultValue = "1") long round){
        if(idStatusAdmin2 == 0){
            List<HowManyApplication> howManyApplicationList = new ArrayList<>();
            HowManyApplication howManyApplication;
            List<Posting> posting = repoPosting.findAll();
            for(Posting postingPerLine:posting){
                howManyApplication = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(),idStatusAdmin1,round);
                if(!howManyApplication.getWhoApplicationList().isEmpty()){
                    howManyApplicationList.add(howManyApplication);
                }
            }
            List<Employer> employerList = new ArrayList<>();
            for(HowManyApplication howManyApplicationPerLine:howManyApplicationList){
                Posting postingEmployer = repoPosting.findById(howManyApplicationPerLine.getIdPosting()).orElse(null);
                Employer employer = repoEmployer.findById(postingEmployer.getIdEmployer()).orElse(null);
                employerList.add(employer);
            }
            return employerList;
        }else{
            List<HowManyApplication> howManyApplicationList = new ArrayList<>();
            HowManyApplication howManyApplication1;
            HowManyApplication howManyApplication2;
            List<Posting> posting = repoPosting.findAll();
            for(Posting postingPerLine:posting) {
                howManyApplication1 = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(), idStatusAdmin1,round);
                howManyApplication2 = setAllWorkerWithIdStatusAdmin(postingPerLine.getIdPosting(), idStatusAdmin2,round);
                for(WhoApplication whoApplicationPerLine:howManyApplication2.getWhoApplicationList()){
                    howManyApplication1.getWhoApplicationList().add(whoApplicationPerLine);
                }
                if(!howManyApplication1.getWhoApplicationList().isEmpty()){
                    howManyApplicationList.add(howManyApplication1);
                }
            }
            List<Employer> employerList = new ArrayList<>();
            for(HowManyApplication howManyApplicationPerLine:howManyApplicationList){
                Posting postingEmployer = repoPosting.findById(howManyApplicationPerLine.getIdPosting()).orElse(null);
                Employer employer = repoEmployer.findById(postingEmployer.getIdEmployer()).orElse(null);
                employerList.add(employer);
            }
            return employerList;
        }
    }

    @GetMapping("/emp/showAllWorkerByIdPostingAllStatus")
    public HowManyApplication showAllWorkerByIdPostingAllStatus(@RequestParam(name = "idPosting") long idPosting){
        return setAllWorker(idPosting,0,0);
    }

    public HowManyApplication setAllWorker(long idPosting,long idStatus,long round){
        List<WhoApplication> whoApplicationList = new ArrayList<>();
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        List<Application> applicationList = posting.getApplicationList();
        long count = 1;
        if(round == 0){
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
            }else if(idStatus == 15){
                for(Application applicationPerLine : applicationList){
                    if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                        count = getCount(whoApplicationList, count, applicationPerLine);
                    }
                }
            }else if(idStatus == 16){
                for(Application applicationPerLine : applicationList){
                    if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                        count = getCount(whoApplicationList, count, applicationPerLine);
                    }
                }
            }else if(idStatus == 20){
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
            }else if(idStatus == 22){
                for(Application applicationPerLine : applicationList){
                    if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                        count = getCount(whoApplicationList, count, applicationPerLine);
                    }
                }
            }else if(idStatus == 23){
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
            }else if(idStatus == 25){
                for(Application applicationPerLine : applicationList){
                    if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                        count = getCount(whoApplicationList, count, applicationPerLine);
                    }
                }
            }else if(idStatus == 26){
                for(Application applicationPerLine : applicationList){
                    if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                        count = getCount(whoApplicationList, count, applicationPerLine);
                    }
                }
            }
        }else{
            List<PostingOpenClose> postingOpenCloseList = repoPostingOpenClose.findByPosting(posting);
            for(PostingOpenClose postingOpenClosePerLine:postingOpenCloseList){
                if(round == postingOpenClosePerLine.getRound()){
                    if(idStatus == 0){
                        for(Application applicationPerLine : applicationList){
                            if(round == applicationPerLine.getRound()){
                                count = getCount(whoApplicationList, count, applicationPerLine);
                            }
                        }
                    }else if(idStatus == 11){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 12){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 13){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 14){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 15){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 16){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 20){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 21){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 22){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 23){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 24){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 25){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }else if(idStatus == 26){
                        for(Application applicationPerLine : applicationList){
                            if(applicationPerLine.getStatus().getIdStatus() == idStatus){
                                if(round == applicationPerLine.getRound()){
                                    count = getCount(whoApplicationList, count, applicationPerLine);
                                }
                            }
                        }
                    }
                }
            }
        }
        HowManyApplication howManyApplication = new HowManyApplication(idPosting,whoApplicationList);
        return howManyApplication;
    }

    public HowManyApplication setAllWorkerWithIdStatusAdmin( long idPosting,long idStatusAdmin,long round){
        List<WhoApplication> whoApplicationList = new ArrayList<>();
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        List<Application> applicationList = posting.getApplicationList();
        long count = 1;
        for(Application applicationPerLine:applicationList){
            if(applicationPerLine.getIdStatusAdmin() == idStatusAdmin){
                if(applicationPerLine.getRound() == round){
                    count = getCount(whoApplicationList,count,applicationPerLine);
                }
            }
        }
        HowManyApplication howManyApplication = new HowManyApplication(idPosting,whoApplicationList);
        return howManyApplication;
    }

    public long getCount(List<WhoApplication> whoApplicationList, long count, Application applicationPerLine) {
        Worker worker = repoWorker.findById(applicationPerLine.getIdWorker()).orElse(null);
        String statusAdminName = "";
        if(repoStatus.findById(applicationPerLine.getIdStatusAdmin()).orElse(null) != null){
            statusAdminName = repoStatus.findById(applicationPerLine.getIdStatusAdmin()).orElse(null).getStatusName();
        }
        WhoApplication whoApplication = new WhoApplication(count,applicationPerLine.getIdApplication(),
                applicationPerLine.getPosting().getEmployer().getEstablishmentName(),applicationPerLine.getPosting().getEmployer().getEntrepreneurfName(),
                applicationPerLine.getPosting().getEmployer().getEntrepreneurlName(),applicationPerLine.getPosting().getEmployer().getAddress(),
                applicationPerLine.getPosting().getEmployer().getProvince().getProvinceName(),applicationPerLine.getPosting().getEmployer().getDistrict().getDistrictName(),
                applicationPerLine.getPosting().getEmployer().getSubDistrict().getSubDistrict(),applicationPerLine.getPosting().getEmployer().getSubDistrict().getPostcode(),
                applicationPerLine.getRound(),applicationPerLine.getDate(),
                worker.getIdWorker(),worker.getRate(),worker.getIdentificationNumber(),
                worker.getVerifyPic(),worker.getSex(),worker.getFirstName(),worker.getMiddleName(),worker.getLastName(),
                worker.getPhone(),worker.getWorkerType(),worker.getNationality(),applicationPerLine.getStatus().getIdStatus(),applicationPerLine.getStatus().getStatusName(),
                applicationPerLine.getIdStatusAdmin(),
                statusAdminName);
        if(applicationPerLine.getActToRegister()!=null){
            if(applicationPerLine.getActToRegister().getDescription()!=null){
                whoApplication.setDescription(applicationPerLine.getActToRegister().getDescription());
            }
        }
        if(applicationPerLine.getApplicationHasComment().getDescriptionBreakShort()!=null){
            whoApplication.setDescriptionBreakShort(applicationPerLine.getApplicationHasComment().getDescriptionBreakShort());
        }
        if(applicationPerLine.getApplicationHasComment().getDescriptionRejectOnSite()!=null){
            whoApplication.setDescriptionRejectOnSite(applicationPerLine.getApplicationHasComment().getDescriptionRejectOnSite());
        }
        if(applicationPerLine.getApplicationHasComment().getDescriptionRejectOnWeb()!=null){
            whoApplication.setDescriptionRejectOnWeb(applicationPerLine.getApplicationHasComment().getDescriptionRejectOnWeb());
        }
        Posting posting = repoPosting.findById(applicationPerLine.getIdPosting()).orElse(null);
        PostingOpenClose postingOpenClose = repoPostingOpenClose.findByPostingAndRound(posting,applicationPerLine.getRound());
        whoApplication.setActiveDate(postingOpenClose.getActiveDate());
        if(postingOpenClose.getInactiveDate()!=null){
            whoApplication.setInActiveDate(postingOpenClose.getInactiveDate());
        }
        if(repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(applicationPerLine.getWorker(),applicationPerLine.getPosting().getEmployer(),"ROLE_WORKER",posting) != null){
            Ratings ratingEmpGiveWorker = repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(applicationPerLine.getWorker(),
                    applicationPerLine.getPosting().getEmployer(),"ROLE_WORKER",posting);
            whoApplication.setScoreEmpGiveWorker(ratingEmpGiveWorker.getRate());
            whoApplication.setCommentEmpGiveWorker(ratingEmpGiveWorker.getComment());
        }
        if(repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(applicationPerLine.getWorker(),applicationPerLine.getPosting().getEmployer(),"ROLE_EMP",posting) != null){
            Ratings ratingWorkerGiveEmp = repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(applicationPerLine.getWorker(),
                    applicationPerLine.getPosting().getEmployer(),"ROLE_EMP",posting);
            whoApplication.setScoreWorkerGiveEmp(ratingWorkerGiveEmp.getRate());
            whoApplication.setCommentWorkerGiveEmp(ratingWorkerGiveEmp.getComment());
        }

        whoApplicationList.add(whoApplication);
        count++;
        return count;
    }
    //-------------------------------------------------------------------------------------------------
    @PutMapping("/emp/employerAcceptOnWeb")//Employer--------------------------------------------- Done
    public Application employerAcceptOnWeb(@RequestParam(value = "idApplication") long idApplication) throws Exception {
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(14L).orElse(null);//Wating_EmployerSummary
        application.setStatus(status);
        application.setIdStatusAdmin(27);
        emailBusiness.sendApplicationAcceptOnWeb(application.getWorker().getAccount().getEmail(),application.getWorker().getFirstName()+" "+application.getWorker().getLastName());
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
    @PutMapping("/admin/adminSendWorkerToEmployer")//Admin------------------------------------------
    public Application adminSendWorkerToEmployer(@RequestParam(value = "idApplication") long idApplication){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Status status = repoStatus.findById(14L).orElse(null);//Wating_EmployerSummary
        application.setStatus(status);
        application.setIdStatusAdmin(28);
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
        Status status = repoStatus.findById(16L).orElse(null);//Reject_WorkerOnSite
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
        Status status = repoStatus.findById(23L).orElse(null);//BreakShort
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
        ZonedDateTime date = ZonedDateTime.now();
        Ratings newRating = new Ratings(ratings.getRate(),ratings.getComment(),date,roleWorker.getRoleName(),application.getPosting().getEmployer(),application.getWorker(),application.getPosting());
        if( repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName(), application.getPosting() ) !=null ){
            return "You give score already.";
        }
        repoRatings.save(newRating);
        if(repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName(), application.getPosting() ) !=null &&
                repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName(), application.getPosting() ) !=null){
            if(application.getStatus().getIdStatus()==23){
                Status status = repoStatus.findById(29L).orElse(null);//DoneBreakShort
                application.setStatus(status);
                repoApplication.save(application);
            }else{
                Status status = repoStatus.findById(20L).orElse(null);//Done
                application.setStatus(status);
                repoApplication.save(application);
            }
        }else{
            Status status = repoStatus.findById(26L).orElse(null);//empRated
            application.setStatus(status);
            repoApplication.save(application);
        }
        Worker worker = repoWorker.findById(application.getWorker().getIdWorker()).orElse(null);
        List<Ratings> ratingsList = repoRatings.findByWorkerAndForwho(worker,worker.getAccount().getRole().getRoleName());
        worker.setRate(returnScoreDouble(ratingsList));
        repoWorker.save(worker);
        return "Score = " + ratings.getRate();
    }

    @PutMapping("/worker/workerGiveScoreToEmployer")//Worker--------------------------------------------- Done
    public String workerGiveScoreToEmployer(@RequestParam(value = "idApplication") long idApplication,
                                            @RequestBody Ratings ratings){
        Application application = repoApplication.findById(idApplication).orElse(null);
        Role roleEmployer = repoRole.findById(2L).orElse(null);//Employer
        Role roleWorker = repoRole.findById(3L).orElse(null);//Worker
        ZonedDateTime date = ZonedDateTime.now();
        Ratings newRating = new Ratings(ratings.getRate(),ratings.getComment(),date,roleEmployer.getRoleName(),application.getPosting().getEmployer(),application.getWorker(), application.getPosting());
        if( repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName(), application.getPosting() ) !=null ){
            return "You give score already.";
        }
        repoRatings.save(newRating);
        if(repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleEmployer.getRoleName(), application.getPosting() ) !=null &&
                repoRatings.findByWorkerAndEmployerAndForwhoAndPosting(application.getWorker(), application.getPosting().getEmployer(), roleWorker.getRoleName(), application.getPosting() ) !=null){
            if(application.getStatus().getIdStatus()==23){
                Status status = repoStatus.findById(29L).orElse(null);//DoneBreakShort
                application.setStatus(status);
                repoApplication.save(application);
            }else{
                Status status = repoStatus.findById(20L).orElse(null);//Done
                application.setStatus(status);
                repoApplication.save(application);
            }
        }else{
            Status status = repoStatus.findById(25L).orElse(null);//workerRated
            application.setStatus(status);
            repoApplication.save(application);
        }
        Employer employer = repoEmployer.findById(application.getPosting().getEmployer().getIdEmployer()).orElse(null);
        List<Ratings> ratingsList = repoRatings.findByEmployerAndForwho(employer,employer.getAccount().getRole().getRoleName());
        employer.setRate(returnScoreDouble(ratingsList));
        repoEmployer.save(employer);
        return "Score = " + ratings.getRate();
    }
    //-------------------------------------------------------------------------------------------------
    public Double returnScoreDouble(List<Ratings> ratingsList){
        double totalScore = 0;
        double count = 0;
        for (Ratings ratingPerLine : ratingsList) {
            totalScore += ratingPerLine.getRate();
            count++;
        }
        double realScore = totalScore/count;
        return realScore;
    }
}
