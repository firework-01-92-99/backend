package senior.project.firework.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.*;
import senior.project.firework.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import senior.project.firework.exceptions.AccountException;
import senior.project.firework.services.EmailBusiness;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    @Autowired
    private repoApprove repoApprove;
    @Autowired
    private repoStatus repoStatus;
    @Autowired
    private repoWorkerType repoWorkerType;
    @Autowired
    private repoNationality repoNationality;
    @Autowired
    private repoBusinesstype repoBusinesstype;
    @Autowired
    private repoProvince repoProvince;
    @Autowired
    private repoDistrict repoDistrict;
    @Autowired
    private repoSubDistrict repoSubDistrict;
    @Autowired
    EmailBusiness emailBusiness;

    @GetMapping("/main/allAccount")
    public List<Account> allAccount(){
        return repoAccount.findAll();
    }

    @GetMapping("/admin/selectAccount")
    public Optional<Account> selectAccount(@RequestParam(name = "idAccount") long idAccount){
        return repoAccount.findById(idAccount);
    }

    @PostMapping("/main/register")
    public void RegisAccount(@RequestBody Account account) throws Exception {
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

    public void RegisAccountForEmployer(Account account,String encodedpassword) throws Exception {
        Status status = repoStatus.findById(6L).orElse(null);
        Approve approve = new Approve(status);
        repoApprove.save(approve);
        Account newAccount = new Account(account.getUsername(),encodedpassword,account.getRole(),approve);
        repoAccount.save(newAccount);

        Businesstype businesstype = repoBusinesstype.findById(account.getEmployer().getBusinesstype().getIdBusinessType()).orElse(null);
        Province province = repoProvince.findById(account.getEmployer().getProvince().getIdProvince()).orElse(null);
        District district = repoDistrict.findById(account.getEmployer().getDistrict().getIdDistrict()).orElse(null);
        SubDistrict subDistrict = repoSubDistrict.findById(account.getEmployer().getSubDistrict().getIdSubdistrict()).orElse(null);
        Nationality nationality = repoNationality.findById(account.getEmployer().getNationality().getIdnationality()).orElse(null);

        Employer employer = new Employer(account.getEmployer().getEstablishmentName(),account.getEmployer().getEntrepreneurfName(),
                account.getEmployer().getEntrepreneurlName(),account.getEmployer().getAddress(),account.getEmployer().getTel(),
                account.getEmployer().getPhone(),account.getEmployer().getEmail(),account.getEmployer().getLineId(),
                businesstype,newAccount,province, district,subDistrict,nationality);

        repoEmployer.save(employer);
        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        emailBusiness.sendActivateUserEmail(account.getEmployer().getEmail(), account.getEmployer().getEstablishmentName(), otp);
    }

    public void RegisAccountForWorker(Account account,String encodedpassword) throws Exception {
        Status status = repoStatus.findById(6L).orElse(null);
        Approve approve = new Approve(status);
        repoApprove.save(approve);
        Account newAccount = new Account(account.getUsername(),encodedpassword,account.getRole(),approve);
        repoAccount.save(newAccount);

        Nationality nationality = repoNationality.findById(account.getWorker().getNationality().getIdnationality()).orElse(null);
        WorkerType workerType = repoWorkerType.findById(account.getWorker().getWorkerType().getIdWorkerType()).orElse(null);

        Worker worker = new Worker(account.getWorker().getIdentificationNumber(),account.getWorker().getVerifyPic(),
                account.getWorker().getSex(),account.getWorker().getFirstName(),account.getWorker().getMiddleName(),
                account.getWorker().getLastName(),account.getWorker().getPhone(),newAccount,nationality,workerType);

        repoWorker.save(worker);
        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        emailBusiness.sendActivateUserEmail(account.getUsername(), account.getWorker().getFirstName(), otp);
    }

    @GetMapping(value = "/allroles/me")
    public Account ReturnUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByUsername(username);
        return account;
    }
}
