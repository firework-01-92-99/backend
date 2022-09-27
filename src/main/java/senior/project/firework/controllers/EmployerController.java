package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.repositories.repoEditEmployer;
import senior.project.firework.repositories.repoProvince;
import senior.project.firework.repositories.repoSubDistrict;
import senior.project.firework.repositories.repoDistrict;

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
    @Autowired
    private repoEditEmployer repoEditEmployer;
    @Autowired
    private repoProvince repoProvince;
    @Autowired
    private repoDistrict repoDistrict;
    @Autowired
    private repoSubDistrict repoSubDistrict;


    @GetMapping("/admin/allEmployer")
    public List<Employer> allEmployer(){
        return repoEmployer.findAll();
    }

    @GetMapping("/admin/selectEmployer")
    public Optional<Employer> selectEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        return repoEmployer.findById(idEmployer);
    }

    @PutMapping("/emp/deleteMyEmployer")
    public void deleteMyEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(8L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PutMapping("/admin/deleteEmployer")
    public void deleteEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(9L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PostMapping("/emp/editMyEmployer")
    public void editMyEmployer(@RequestBody Employer employer){
        Status status = repoStatus.findById(7L).orElse(null);
        Employer employerForAccount = repoEmployer.findById(employer.getIdEmployer()).orElse(null);
        Account account = repoAccount.findById(employerForAccount.getAccount().getIdAccount()).orElse(null);
        if(account.getApprove().getStatus() == status){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_EDIT, "Can't edit because your Account wait Edit!");
        }
        account.getApprove().setStatus(status);
        EditEmployer editEmployer = new EditEmployer(employer.getEstablishmentName(),employer.getEntrepreneurfName(),employer.getEntrepreneurlName(),
                employer.getAddress(),employer.getTel(),employer.getPhone(),employer.getTel(),employer.getLineId(),employer.getVerifyCert(),
                employer.getProvince().getProvinceName(),employer.getDistrict().getDistrictName(),employer.getSubDistrict().getSubDistrict(),
                employer.getSubDistrict().getPostcode(),employer);
        repoEditEmployer.save(editEmployer);
        repoAccount.save(account);
    }

    @PutMapping("/admin/youCanEditEmployer")
    public void youCanEditEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(4L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        long idEditEmployer = repoEditEmployer.getMaxIdEmployerByEmployer(employer);
        EditEmployer editEmployer = repoEditEmployer.findById(idEditEmployer).orElse(null);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);

        String newText1 = editEmployer.getEstablishmentName();
        String newText2 = editEmployer.getEntrepreneurfName();
        String newText3 = editEmployer.getEntrepreneurlName();
        String newText4 = editEmployer.getAddress();
        String newText5 = editEmployer.getTel();
        String newText6 = editEmployer.getPhone();
        String newText7 = editEmployer.getEmail();
        String newText8 = editEmployer.getLineId();
        String newText9 = editEmployer.getVerifyCert();
        Province newText10 = repoProvince.findByProvinceName(editEmployer.getProvinceName());
        District newText11 = repoDistrict.findByDistrictName(editEmployer.getDistrictName());
        SubDistrict newText12 = repoSubDistrict.findByPostcode(editEmployer.getPostcode());

        String oldText1 = employer.getEstablishmentName();
        String oldText2 = employer.getEntrepreneurfName();
        String oldText3 = employer.getEntrepreneurlName();
        String oldText4 = employer.getAddress();
        String oldText5 = employer.getTel();
        String oldText6 = employer.getPhone();
        String oldText7 = employer.getEmail();
        String oldText8 = employer.getLineId();
        String oldText9 = employer.getVerifyCert();
        Province oldText10 = employer.getProvince();
        District oldText11 = employer.getDistrict();
        SubDistrict oldText12 = employer.getSubDistrict();
        String oldText13 = employer.getSubDistrict().getPostcode();

        editEmployer.setEstablishmentName(oldText1);
        editEmployer.setEntrepreneurfName(oldText2);
        editEmployer.setEntrepreneurlName(oldText3);
        editEmployer.setAddress(oldText4);
        editEmployer.setTel(oldText5);
        editEmployer.setTel(oldText6);
        editEmployer.setEmail(oldText7);
        editEmployer.setLineId(oldText8);
        editEmployer.setVerifyCert(oldText9);
        editEmployer.setProvinceName(oldText10.getProvinceName());
        editEmployer.setDistrictName(oldText11.getDistrictName());
        editEmployer.setSubDistrict(oldText12.getSubDistrict());
        editEmployer.setPostcode(oldText13);
        repoEditEmployer.save(editEmployer);

        employer.setEstablishmentName(newText1);
        employer.setEntrepreneurfName(newText2);
        employer.setEntrepreneurlName(newText3);
        employer.setAddress(newText4);
        employer.setTel(newText5);
        employer.setTel(newText6);
        employer.setEmail(newText7);
        employer.setLineId(newText8);
        employer.setVerifyCert(newText9);
        employer.setProvince(newText10);
        employer.setDistrict(newText11);
        employer.setSubDistrict(newText12);
        repoEmployer.save(employer);

        account.getApprove().setStatus(status);
        repoAccount.save(account);

    }

    @DeleteMapping("/admin/youCanNotEditEmployer")
    public void youCanNotEditEmployer(@RequestParam(name = "idEmployer") long idEmployer){
        Status status = repoStatus.findById(4L).orElse(null);
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        long idEditEmployer = repoEditEmployer.getMaxIdEmployerByEmployer(employer);
        Account account = repoAccount.findById(employer.getAccount().getIdAccount()).orElse(null);
        repoEditEmployer.deleteById(idEditEmployer);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }
}
