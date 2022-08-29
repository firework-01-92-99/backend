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

    @GetMapping("/admin/allAdmin")
    public List<Admin> allAdmin(){
        return repoAdmin.findAll();
    }

    @GetMapping("/admin/selectAdmin")
    public Optional<Admin> selectAdmin(@RequestParam(name = "idAdmin") long idAdmin){
        return repoAdmin.findById(idAdmin);
    }
    
    @PostMapping("/admin/approveAccount")
    public Approve approveAccount(@RequestParam(name = "idAdmin") long idAdmin,@RequestParam(name = "idAccount") long idAccount,@RequestParam(name = "idStatus") long idStatus){
        Optional<Admin> admin = repoAdmin.findById(idAdmin);
        Admin admin1 = new Admin(admin.get().getIdAdmin(),admin.get().getUsername(),admin.get().getPassword(),admin.get().getFirstName(),
                admin.get().getLastName(),admin.get().getApproveList(),admin.get().getApplicationList(),admin.get().getRole());
        Optional<Account> account = repoAccount.findById(idAccount);
        Account account1 = new Account(account.get().getIdAccount(),account.get().getUsername(),account.get().getPassword(),account.get().getRole(),
                account.get().getApprove(),account.get().getEmployer(),account.get().getWorker());
        Optional<Status> status = repoStatus.findById(idStatus);
        Status status1 = new Status(status.get().getIdStatus(),status.get().getStatusName(),status.get().getApproveList(),status.get().getApplicationList(),
                status.get().getPostingList());
        Approve approve = new Approve(admin1,status1,account1);
        return repoApprove.save(approve);
    }

    @PutMapping(value = "/admin/editApprove")
    public Approve Editapprove(@RequestParam(name = "idApprove") long idApprove){
        Optional<Approve> approve = repoApprove.findById(idApprove);
        Approve approve1 = new Approve(approve.get().getIdApprove(),approve.get().getAdmin(),approve.get().getStatus(),approve.get().getAccount());
        return repoApprove.save(approve1);
    }
}
