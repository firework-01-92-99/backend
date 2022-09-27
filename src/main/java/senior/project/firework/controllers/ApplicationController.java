package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.frontendmodel.StatusApplication;
import senior.project.firework.frontendmodel.HowManyApplication;
import senior.project.firework.frontendmodel.WhoApplication;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoApplication;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoPosition;

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

    @GetMapping("/admin/allApplication")
    public List<Application> allApplication(){
        return repoApplication.findAll();
    }

    @PostMapping("/worker/workApp")
    public Application workerApplication(@RequestParam(value = "idWorker") long idWorker,
                                         @RequestParam(value = "idPosting") long idPosting){
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Posting post = repoPosting.findById(idPosting).orElse(null);
        Status status = repoStatus.findById(3L).orElse(null);
        Application newApplication = new Application(worker,post,status);
        return repoApplication.save(newApplication);
    }

    @DeleteMapping("/worker/workCancelApp")
    public String workerCancelApplication(@RequestParam(value = "idApplication") long idApplication){
        Application delApplication = repoApplication.findById(idApplication).orElse(null);
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

    @GetMapping("/admin_emp/showAllWorker")
    public HowManyApplication showAllWorker(@RequestParam(name = "idPosting") long idPosting){
        return setAllWorker(idPosting);
    }

    public HowManyApplication setAllWorker(long idPosting){
        List<WhoApplication> whoApplicationList = new ArrayList<>();
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        List<Application> applicationList = posting.getApplicationList();
        long count = 1;
        for(Application applicationPerLine : applicationList){
            Worker worker = repoWorker.findById(applicationPerLine.getIdWorker()).orElse(null);
            WhoApplication whoApplication = new WhoApplication(count,worker.getIdWorker(),worker.getIdentificationNumber(),
                    worker.getVerifyPic(),worker.getSex(),worker.getFirstName(),worker.getMiddleName(),worker.getLastName(),
                    worker.getPhone(),worker.getWorkerType(),worker.getNationality(),applicationPerLine.getStatus().getStatusName());
            whoApplicationList.add(whoApplication);
            count++;
        }
        HowManyApplication howManyApplication = new HowManyApplication(idPosting,whoApplicationList);
        return howManyApplication;
    }

}
