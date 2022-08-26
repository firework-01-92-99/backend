package senior.project.firework.controllers;

import org.hibernate.jdbc.Work;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.Account;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Worker;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoEmployer;
import org.springframework.beans.factory.annotation.Autowired;

import senior.project.firework.exceptions.AccountException;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/allAccount")
    public List<Account> allAccount(){
        return repoAccount.findAll();
    }

    @GetMapping("/admin/selectAccount")
    public Optional<Account> selectAccount(@RequestParam(name = "idAccount") long idAccount){
        return repoAccount.findById(idAccount);
    }

    @PostMapping("/main/register")
    public void RegisAccount(@RequestBody Account account){
        if(repoAccount.findByUsername(account.getUsername()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.USERNAME_HAVE_ALREADY,"Username have already!");
        }
        String encodedpassword = passwordEncoder.encode(account.getPassword());
        if(account.getRole().getIdRole() == 2){
            RegisAccountForEmployer(account,encodedpassword);
        }else if(account.getRole().getIdRole() == 3){
            RegisAccountForWorker(account,encodedpassword);
        }
    }

    public void RegisAccountForEmployer(Account account,String encodedpassword){
        Employer newEmployer = new Employer(account.getEmployer().getEstablishmentName(),account.getEmployer().getEntrepreneurfName(),account.getEmployer().getEntrepreneurlName(),
                account.getEmployer().getAddress(),account.getEmployer().getTel(),account.getEmployer().getPhone(),account.getEmployer().getLineId(),
                account.getEmployer().getBusinesstype(),account.getEmployer().getProvince(),account.getEmployer().getDistrict(),account.getEmployer().getSubDistrict());
        repoEmployer.save(newEmployer);
        Account newAccount = new Account(account.getUsername(),encodedpassword,account.getRole(),account.getEmployer());
        repoAccount.save(newAccount);
    }

    public void RegisAccountForWorker(Account account,String encodedpassword){
        Worker newWorker = new Worker(account.getWorker().getIdentificationNumber(),account.getWorker().getSex(),
                account.getWorker().getFirstName(),account.getWorker().getMiddleName(),account.getWorker().getLastName(),account.getWorker().getPhone());
        repoWorker.save(newWorker);
        Account newAccount = new Account(account.getUsername(),encodedpassword,account.getRole(),account.getWorker());
        repoAccount.save(newAccount);
    }
}
