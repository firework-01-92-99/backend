package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.models.Account;
import senior.project.firework.models.Admin;
import senior.project.firework.models.Approve;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoAdmin;
import senior.project.firework.repositories.repoApprove;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.services.EmailBusiness;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private repoAdmin repoAdmin;
    @Autowired
    private repoApprove repoApprove;
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoStatus repoStatus;
    @Autowired
    private EmailBusiness emailBusiness;

    @GetMapping("/admin/allAdmin")
    public List<Admin> allAdmin(){
        return repoAdmin.findAll();
    }

    @GetMapping("/admin/selectAdmin")
    public Optional<Admin> selectAdmin(@RequestParam(name = "idAdmin") long idAdmin){
        return repoAdmin.findById(idAdmin);
    }
    
    @PutMapping("/admin/approveAccount")
    public Approve approveAccount(@RequestParam(name = "idApprove") long idApprove,
                                  @RequestParam(name = "idAdmin") long idAdmin,
                                  @RequestParam(name = "idStatus") long idStatus) throws Exception {
        Approve approve = repoApprove.findById(idApprove).orElse(null);
        Admin admin = repoAdmin.findById(idAdmin).orElse(null);
        Status status = repoStatus.findById(idStatus).orElse(null);
        Account account = repoAccount.findByEmail(approve.getAccount().getEmail());
        approve.setAdmin(admin);
        approve.setStatus(status);
        if(approve.getAccount().getEmployer() != null){
            if(idStatus == 4){
                emailBusiness.sendApproveAlready(approve.getAccount().getEmail(),approve.getAccount().getEmployer().getEstablishmentName());
            }else if(idStatus == 5){
                emailBusiness.sendReject(approve.getAccount().getEmail(),approve.getAccount().getEmployer().getEstablishmentName());
                account.setEmail("-");
                repoAccount.save(account);
            }
        }else if(approve.getAccount().getWorker() != null){
            if(idStatus == 4){
                emailBusiness.sendApproveAlready(approve.getAccount().getEmail(),approve.getAccount().getWorker().getFirstName());
            }else if(idStatus == 5){
                emailBusiness.sendReject(approve.getAccount().getEmail(),approve.getAccount().getWorker().getFirstName());
                account.setEmail("-");
                repoAccount.save(account);
            }
        }
        return repoApprove.save(approve);
    }

    @PutMapping(value = "/admin/editApprove")
    public Approve editApprove(@RequestParam(name = "idApprove") long idApprove){
        Approve approve = repoApprove.findById(idApprove).orElse(null);
        return repoApprove.save(approve);
    }
}
