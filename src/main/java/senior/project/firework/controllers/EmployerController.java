package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Account;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoAccount;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployerController {
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoStatus repoStatus;
    @Autowired
    private repoAccount repoAccount;

    @GetMapping("/main/allEmployer")
    public List<Employer> allEmployer(){
        return repoEmployer.findAll();
    }

    @GetMapping("/main/selectEmployer")
    public Optional<Employer> selectEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        return repoEmployer.findById(idEmployer);
    }

    @PutMapping("/main/deleteMyEmployer")
    public void deleteMyEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(8L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PutMapping("/main/deleteEmployer")
    public void deleteEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(9L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

}
