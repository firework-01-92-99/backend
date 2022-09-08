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
    
    @PutMapping("/main/approveAccount")
    public Approve approveAccount(@RequestParam(name = "idApprove") long idApprove,@RequestParam(name = "idAdmin") long idAdmin,@RequestParam(name = "idStatus") long idStatus){
        Approve approve = repoApprove.findById(idApprove).orElse(null);
        Admin admin = repoAdmin.findById(idAdmin).orElse(null);
        Status status = repoStatus.findById(idStatus).orElse(null);
        approve.setAdmin(admin);
        approve.setStatus(status);
        return repoApprove.save(approve);
    }

    @PutMapping(value = "/admin/editApprove")
    public Approve Editapprove(@RequestParam(name = "idApprove") long idApprove){
        Approve approve = repoApprove.findById(idApprove).orElse(null);
        return repoApprove.save(approve);
    }
}
