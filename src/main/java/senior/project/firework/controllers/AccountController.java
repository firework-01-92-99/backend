package senior.project.firework.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.*;
import senior.project.firework.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import senior.project.firework.services.StorageService;

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
    private repoAdmin repoAdmin;
    @Autowired
    private EmailBusiness emailBusiness;
    @Autowired
    private repoOTP repoOTP;
    @Autowired
    StorageService storageService;

    @GetMapping("/main/allAccount")
    public List<Account> allAccount(){
        return repoAccount.findAll();
    }

    @GetMapping("/admin/selectAccount")
    public Optional<Account> selectAccount(@RequestParam(name = "idAccount") long idAccount){
        return repoAccount.findById(idAccount);
    }

    @PostMapping(value = "/main/register",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void RegisAccount(@RequestParam(value = "image",required = false) MultipartFile imageFile,@RequestPart Account account) throws Exception {
        if(repoAccount.findByEmail(account.getEmail()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_EMAIL_HAVE_ALREADY,"Email have already!");
        }else if(imageFile == null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_IMAGE_IS_NULL,"Can't add Image");
        }
        String encodedpassword = passwordEncoder.encode(account.getPassword());
        if(account.getRole().getIdRole() == 2){
            RegisAccountForEmployer(account,encodedpassword,imageFile);
        }else if(account.getRole().getIdRole() == 3){
            RegisAccountForWorker(account,encodedpassword,imageFile);
        }
    }

    public void RegisAccountForEmployer(Account account,String encodedpassword,MultipartFile imageFile) throws Exception {
        account.getEmployer().setProfile(storageService.store(imageFile,account.getIdAccount()));
        Status status = repoStatus.findById(10L).orElse(null);
        Approve approve = new Approve(status);
        repoApprove.save(approve);
        Account newAccount = new Account(account.getEmail(),encodedpassword,account.getRole(),approve);
        repoAccount.save(newAccount);

        Businesstype businesstype = repoBusinesstype.findById(account.getEmployer().getBusinesstype().getIdBusinessType()).orElse(null);
        Province province = repoProvince.findById(account.getEmployer().getProvince().getIdProvince()).orElse(null);
        District district = repoDistrict.findById(account.getEmployer().getDistrict().getIdDistrict()).orElse(null);
        SubDistrict subDistrict = repoSubDistrict.findById(account.getEmployer().getSubDistrict().getIdSubdistrict()).orElse(null);
        Nationality nationality = repoNationality.findById(account.getEmployer().getNationality().getIdnationality()).orElse(null);

        Employer employer = new Employer(account.getEmployer().getEstablishmentName(),account.getEmployer().getEntrepreneurfName(),
                account.getEmployer().getEntrepreneurlName(),account.getEmployer().getAddress(),account.getEmployer().getTel(),
                account.getEmployer().getPhone(),account.getEmployer().getEmail(),account.getEmployer().getLineId(),account.getEmployer().getProfile(),
                businesstype,newAccount,province, district,subDistrict,nationality);

        System.out.println(account.getEmail());
        repoEmployer.save(employer);

        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        emailBusiness.sendActivateUserEmail(account.getEmail(), account.getEmployer().getEntrepreneurfName(), otp);

        OTP newOTP = new OTP(randomWithNextInt,newAccount);
        repoOTP.save(newOTP);
    }

    public void RegisAccountForWorker(Account account,String encodedpassword,MultipartFile imageFile) throws Exception {
        account.getWorker().setVerifyPic(storageService.store(imageFile,account.getIdAccount()));
        Status status = repoStatus.findById(10L).orElse(null);
        Approve approve = new Approve(status);
        repoApprove.save(approve);
        Account newAccount = new Account(account.getEmail(),encodedpassword,account.getRole(),approve);
        repoAccount.save(newAccount);

        Nationality nationality = repoNationality.findById(account.getWorker().getNationality().getIdnationality()).orElse(null);
        WorkerType workerType = repoWorkerType.findById(account.getWorker().getWorkerType().getIdWorkerType()).orElse(null);
        if(account.getWorker().getMiddleName() == null){
            Worker worker = new Worker(account.getWorker().getIdentificationNumber(),account.getWorker().getVerifyPic(),
                    account.getWorker().getSex(),account.getWorker().getFirstName(),"",
                    account.getWorker().getLastName(),account.getWorker().getPhone(),newAccount,nationality,workerType);
            repoWorker.save(worker);
        }else{
            Worker worker = new Worker(account.getWorker().getIdentificationNumber(),account.getWorker().getVerifyPic(),
                    account.getWorker().getSex(),account.getWorker().getFirstName(),account.getWorker().getMiddleName(),
                    account.getWorker().getLastName(),account.getWorker().getPhone(),newAccount,nationality,workerType);
            repoWorker.save(worker);
        }

        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        emailBusiness.sendActivateUserEmail(account.getEmail(), account.getWorker().getFirstName(), otp);

        OTP newOTP = new OTP(randomWithNextInt,newAccount);
        repoOTP.save(newOTP);
    }

    @PostMapping("/main/receiveOTP")
    public String receiveOTP(@RequestParam(name = "receiveOTP") long receiveOTP,@RequestParam(name = "email") String email) throws Exception {
        Account account = repoAccount.findByEmail(email);
        long maxIdOTP = repoOTP.getMaxIdOTP(account.getIdAccount());
        long maxOTP = repoOTP.findById(maxIdOTP).orElse(null).getOtpNo();
        if(maxOTP == receiveOTP){
            if(account.getApprove().getStatus().equals(repoStatus.findById(3L).orElse(null))){
                Status status = repoStatus.findById(4L).orElse(null);
                account.getApprove().setStatus(status);
                repoAccount.save(account);
                return "OTP correct,You can edit password.";
            }else if(account.getApprove().getStatus().equals(repoStatus.findById(10L).orElse(null))){
                Status status = repoStatus.findById(6L).orElse(null);
                account.getApprove().setStatus(status);
                repoAccount.save(account);
                return "OTP correct,Wait Admin approve.";
            }
        }
        throw new AccountException(ExceptionRepo.ERROR_CODE.OTP_INCORRECT,"OTP incorrect!");
    }

    @PostMapping("/main/sendOTPAgain")
    public String sendOTPAgain(@RequestParam(name = "email") String email) throws Exception {
        Account account = repoAccount.findByEmail(email);

        Random random = new Random();
        int randomWithNextInt = random.nextInt(999999);
        String otp = String.format("%06d", randomWithNextInt);
        String name;
        if(account.getWorker() == null){
            name = account.getEmployer().getEstablishmentName();
        }else{
            name = account.getWorker().getFirstName() + " " + account.getWorker().getLastName();
        }
        emailBusiness.sendOTPAgain(account.getEmail(), name , otp);

        OTP newOTP = new OTP(randomWithNextInt,account);
        repoOTP.save(newOTP);
        System.out.println(email);
        System.out.println(account);
        System.out.println(otp);
        return "Sent OTP again already.";
    }

    @PostMapping("/main/forgetPassword")
    public String forgetPassword(@RequestParam(name = "email") String email) throws Exception {
        List<Account> ListAccount = repoAccount.findAll();
        for(Account accountPerLine:ListAccount){
            if(accountPerLine.getEmail().equals(email)){
                Random random = new Random();
                int randomWithNextInt = random.nextInt(999999);
                String otp = String.format("%06d", randomWithNextInt);
                emailBusiness.sendActivateUserEmail(accountPerLine.getEmail(), accountPerLine.getWorker().getFirstName(), otp);

                OTP newOTP = new OTP(randomWithNextInt,accountPerLine);
                repoOTP.save(newOTP);

                Status status = repoStatus.findById(3L).orElse(null);
                accountPerLine.getApprove().setStatus(status);
                repoAccount.save(accountPerLine);
                return "Wait OTP in email " + accountPerLine.getEmail();
            }
        }
        throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_EMAIL_INCORRECT,"Not have this Email!");
    }

    @PostMapping("/worker/editPasswordWorker")
    public String editPasswordWorker(@RequestParam(name = "currentPassword") String currentPassword,
                               @RequestParam(name = "newPassword") String newPassword,
                               @RequestParam(defaultValue = "0",name = "idWorker") long idWorker) throws Exception {
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findByWorker_IdWorker(idWorker);
        if(!passwordEncoder.matches(currentPassword,account.getPassword())){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_PASSWORD_INCORRECT,"Your current Password incorrect!!");
        }
        String newPasswordEncoder = passwordEncoder.encode(newPassword);
        account.setPassword(newPasswordEncoder);
        repoAccount.save(account);
        emailBusiness.sendAccountChangePassword(account.getEmail(),worker.getFirstName() + " " + worker.getLastName());
        return "Edit Success!";
    }

    @PostMapping("/emp/editPasswordEmployer")
    public String editPasswordEmployer(@RequestParam(name = "currentPassword") String currentPassword,
                               @RequestParam(name = "newPassword") String newPassword,
                               @RequestParam(defaultValue = "0",name = "idEmployer") long idEmployer) throws Exception {
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Account account = repoAccount.findByEmployer_IdEmployer(idEmployer);
        if(!passwordEncoder.matches(currentPassword,account.getPassword())){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_PASSWORD_INCORRECT,"Your current Password incorrect!!");
        }
        String newPasswordEncoder = passwordEncoder.encode(newPassword);
        account.setPassword(newPasswordEncoder);
        repoAccount.save(account);
        emailBusiness.sendAccountChangePassword(account.getEmail(),employer.getEstablishmentName());
        return "Edit Success!";
    }

    @GetMapping(value = "/main/passwordEncoder")
    public String passwordEncoder(@RequestParam(name = "password") String password){
        String newPasswordEncoder = passwordEncoder.encode(password);
        return newPasswordEncoder;
    }

    @PostMapping("/main/newPassword")
    public String newPassword(@RequestParam(name = "newPassword") String newPassword,
                              @RequestParam(name = "email") String email) throws Exception {
        Account account = repoAccount.findByEmail(email);
        String newPasswordEncoder = passwordEncoder.encode(newPassword);
        account.setPassword(newPasswordEncoder);
        repoAccount.save(account);
        return "New password Success!";
    }

    @GetMapping(value = "/allroles/me")
    public Account ReturnUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByEmail(username);
        return account;
    }

    @GetMapping(value = "/allroles/getMyAccountStatus")
    public Status ReturnMyAccountStatus(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByEmail(username);
        return account.getApprove().getStatus();
    }

    @GetMapping(value = "/admin/meAdmin")
    public Admin ReturnAdmin(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByEmail(username);
        Admin admin = repoAdmin.findByEmail(account.getEmail());
        return admin;
    }

    //อย่ายุ่งอันนี้นะ
    @GetMapping("/main/getMyMaxOTP")
    public long getMyMaxOTP(@RequestParam(name = "idAccount") long idAccount){
        long maxIdOTP = repoOTP.getMaxIdOTP(idAccount);
        long maxOTP = repoOTP.findById(maxIdOTP).orElse(null).getOtpNo();
        return maxOTP;
    }
}
