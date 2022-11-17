package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.*;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.repositories.repoEditWorker;
import senior.project.firework.services.EmailBusiness;
import senior.project.firework.services.StorageService;

import java.util.List;

@RestController
public class WorkerController {
    @Autowired
    private repoWorker repoWorker;
    @Autowired
    private repoStatus repoStatus;
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoEditWorker repoEditWorker;
    @Autowired
    private EmailBusiness emailBusiness;
    @Autowired
    StorageService storageService;

    @GetMapping("/admin/allWorker")
    public List<Worker> allWorker(){
        return repoWorker.findAll();
    }

    @GetMapping("/admin_emp/selectWorker")
    public Worker selectWorker(@RequestParam(name = "idWorker") long idWorker){
        return repoWorker.findById(idWorker).orElse(null);
    }

    @PutMapping("/worker/deleteMyWorker")
    public void deleteMyWorker(@RequestParam(name = "idWorker") long idWorker){
        Status status = repoStatus.findById(8L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PutMapping("/admin/DeleteWorker")
    public void DeleteWorker(@RequestParam(name = "idWorker") long idWorker) throws Exception {
        Status status = repoStatus.findById(9L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        emailBusiness.sendAccountDeleted(account.getEmail(), worker.getFirstName()+" "+worker.getLastName());
        account.getApprove().setStatus(status);
        account.setEmail("-");
        repoAccount.save(account);
    }


    @PostMapping(value = "/worker/editMyWorker",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void editMyWorker(@RequestParam(value = "image",required = false) MultipartFile imageFile, @RequestPart Worker worker) throws Exception {
        Status status = repoStatus.findById(7L).orElse(null);
        Worker workerForAccount = repoWorker.findById(worker.getIdWorker()).orElse(null);
        Account account = repoAccount.findById(workerForAccount.getAccount().getIdAccount()).orElse(null);
        if(account.getApprove().getStatus() == status){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_EDIT, "Can't edit because your Account wait Edit!");
        }
        account.getApprove().setStatus(status);
        EditWorker editWorker = new EditWorker(worker.getVerifyPic(), worker.getFirstName(),
                worker.getMiddleName(), worker.getLastName(), worker.getPhone(),worker);
        editWorker.setVerifyPic(storageService.store(imageFile,account.getIdAccount()));
        repoEditWorker.save(editWorker);
        repoAccount.save(account);
    }

    @PostMapping(value = "/worker/editMyWorkerWithOutImage")
    public void editMyWorkerWithOutImage(@RequestPart Worker worker) throws Exception {
        Status status = repoStatus.findById(7L).orElse(null);
        Worker workerForAccount = repoWorker.findById(worker.getIdWorker()).orElse(null);
        Account account = repoAccount.findById(workerForAccount.getAccount().getIdAccount()).orElse(null);
        if(account.getApprove().getStatus() == status){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_EDIT, "Can't edit because your Account wait Edit!");
        }
        account.getApprove().setStatus(status);
        EditWorker editWorker = new EditWorker(worker.getVerifyPic(), worker.getFirstName(),
                worker.getMiddleName(), worker.getLastName(), worker.getPhone(),worker);
        repoEditWorker.save(editWorker);
        repoAccount.save(account);
    }

    @PutMapping("/admin/youCanEditWorker")
    public void youCanEditWorker(@RequestParam(name = "idWorker") long idWorker) throws Exception {
        Status status = repoStatus.findById(4L).orElse(null);
        Worker Worker1 = repoWorker.findById(idWorker).orElse(null);
        long idEditWorker = repoEditWorker.getMaxIdWorkerByWorker(Worker1);
        EditWorker editWorker1 = repoEditWorker.findById(idEditWorker).orElse(null);
        Account account = repoAccount.findById(Worker1.getAccount().getIdAccount()).orElse(null);

        String text1 = editWorker1.getVerifyPic();
        String text2 = editWorker1.getFirstName();
        String text3 = editWorker1.getMiddleName();
        String text4 = editWorker1.getLastName();
        String text5 = editWorker1.getPhone();

        String text11 = Worker1.getVerifyPic();
        String text22 = Worker1.getFirstName();
        String text33 = Worker1.getMiddleName();
        String text44 = Worker1.getLastName();
        String text55 = Worker1.getPhone();

        editWorker1.setVerifyPic(text11);
        editWorker1.setFirstName(text22);
        editWorker1.setMiddleName(text33);
        editWorker1.setLastName(text44);
        editWorker1.setPhone(text55);
        repoEditWorker.save(editWorker1);

        Worker1.setVerifyPic(text1);
        Worker1.setFirstName(text2);
        Worker1.setMiddleName(text3);
        Worker1.setLastName(text4);
        Worker1.setPhone(text5);
        repoWorker.save(Worker1);

        account.getApprove().setStatus(status);
        repoAccount.save(account);
        emailBusiness.sendAccountEdited(account.getEmail(),Worker1.getFirstName() + " " + Worker1.getLastName());
    }

    @DeleteMapping("/worker/youCanNotEditWorker")
    public void youCanNotEditWorker(@RequestParam(name = "idWorker") long idWorker) throws Exception {
        Status status = repoStatus.findById(4L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        long idEditWorker = repoEditWorker.getMaxIdWorkerByWorker(worker);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        repoEditWorker.deleteById(idEditWorker);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
        emailBusiness.sendAccountCantEdit(account.getEmail(), worker.getFirstName() + " " + worker.getLastName());
    }

    @PutMapping("/worker/youCanNotDeleteWorker")
    public void youCanNotDeleteWorker(@RequestParam(name = "idWorker") long idWorker) throws Exception {
        Status status = repoStatus.findById(4L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
        emailBusiness.sendAccountCantDelete(account.getEmail(), worker.getFirstName() + " " + worker.getLastName());
    }

    @GetMapping("/admin/getAllEditWorker")
    public List<EditWorker> getAllEditWorker(){
        return repoEditWorker.findAll();
    }

    @GetMapping("/admin/getEditWorkerByIdWorker")
    public List<EditWorker> getEditWorkerByIdWorker(@RequestParam(name = "idWorker") long idWorker){
        return repoEditWorker.getEditWorkerByIdWorker(idWorker);
    }
}