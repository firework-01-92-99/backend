package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Approve;
import senior.project.firework.frontendmodel.WhoInApprove;
import senior.project.firework.repositories.repoApprove;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApproveController {
    @Autowired
    private repoApprove repoApprove;

    @GetMapping("/admin/allApprove")
    public List<Approve> allApprove(){
        return repoApprove.findAll();
    }

    @GetMapping("/main/getAllApproveByIdStatusAndIdRole")
    public List<WhoInApprove> getAllApprove(@RequestParam(name = "idStatus") long idStatus,@RequestParam(name = "idRole") long idRole){
        return setAllApprove(idStatus,idRole);
    }

    public List<WhoInApprove> setAllApprove(long idStatus,long idRole){
        List<Approve> approveList = repoApprove.findAll();
        List<WhoInApprove> whoInApproveList = new ArrayList<>();
        long count = 1;
        for(Approve approvePerLine : approveList){
            if(idRole == 0){
                if(idStatus == 0){
                    WhoInApprove whoInApprove = new WhoInApprove();
                    setWhoInApprove(whoInApprove,count,approvePerLine);
                    whoInApproveList.add(whoInApprove);
                    count++;
                }else{
                    if(approvePerLine.getStatus().getIdStatus() == idStatus){
                        WhoInApprove whoInApprove = new WhoInApprove();
                        setWhoInApprove(whoInApprove,count,approvePerLine);
                        whoInApproveList.add(whoInApprove);
                        count++;
                    }
                }
            }else if(approvePerLine.getAccount().getRole().getIdRole() == idRole){
                if(idStatus == 0){
                    WhoInApprove whoInApprove = new WhoInApprove();
                    setWhoInApprove(whoInApprove,count,approvePerLine);
                    whoInApproveList.add(whoInApprove);
                    count++;
                }else{
                    if(approvePerLine.getStatus().getIdStatus() == idStatus){
                        WhoInApprove whoInApprove = new WhoInApprove();
                        setWhoInApprove(whoInApprove,count,approvePerLine);
                        whoInApproveList.add(whoInApprove);
                        count++;
                    }
                }
            }

        }
        return whoInApproveList;
    }

    public WhoInApprove setWhoInApprove(WhoInApprove whoInApprove,long count,Approve approve){
        if(approve.getAccount().getRole().getIdRole() == 2 /* EMP */){
            whoInApprove.setCount(count);
            whoInApprove.setIdApprove(approve.getIdApprove());
            whoInApprove.setName(approve.getAccount().getEmployer().getEstablishmentName());
            whoInApprove.setWorkOrEmp("Employer");
            whoInApprove.setNationlity(approve.getAccount().getEmployer().getNationality().getNationality_name());
            whoInApprove.setStatus(approve.getStatus().getStatusName());
            whoInApprove.setIdEmpOrWork(approve.getAccount().getEmployer().getIdEmployer());
        }else if(approve.getAccount().getRole().getIdRole() == 3 /* Worker */){
            whoInApprove.setCount(count);
            whoInApprove.setIdApprove(approve.getIdApprove());
            if(approve.getAccount().getWorker().getMiddleName() != null){
                whoInApprove.setName(approve.getAccount().getWorker().getFirstName() + " " + approve.getAccount().getWorker().getMiddleName()
                        + " " + approve.getAccount().getWorker().getLastName());
            }else{
                whoInApprove.setName(approve.getAccount().getWorker().getFirstName() + " " + approve.getAccount().getWorker().getLastName());
            }
            whoInApprove.setWorkOrEmp("Worker");
            whoInApprove.setNationlity(approve.getAccount().getWorker().getNationality().getNationality_name());
            whoInApprove.setStatus(approve.getStatus().getStatusName());
            whoInApprove.setIdEmpOrWork(approve.getAccount().getWorker().getIdWorker());
        }
        return whoInApprove;
    }



}
