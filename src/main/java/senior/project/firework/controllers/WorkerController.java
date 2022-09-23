package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.EditWorker;
import senior.project.firework.models.Status;
import senior.project.firework.models.Worker;
import senior.project.firework.models.Account;
import senior.project.firework.repositories.repoWorker;
import senior.project.firework.repositories.repoStatus;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.repositories.repoEditWorker;

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

    @GetMapping("/admin/allWorker")
    public List<Worker> allWorker(){
        return repoWorker.findAll();
    }

    @GetMapping("/admin/selectWorker")
    public Worker selectWorker(@RequestParam(name = "idWorker") long idWorker){
        return repoWorker.findById(idWorker).orElse(null);
    }

    @PutMapping("/main/deleteMyWorker")
    public void deleteMyWorker(@RequestParam(name = "idWorker") long idWorker){
        Status status = repoStatus.findById(8L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PutMapping("/main/DeleteWorker")
    public void DeleteWorker(@RequestParam(name = "idWorker") long idWorker){
        Status status = repoStatus.findById(9L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }


    @PutMapping("/worker/editMyWorker")
    public void editMyWorker(@RequestBody Worker worker){
        Status status = repoStatus.findById(7L).orElse(null);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        account.getApprove().setStatus(status);
        if(account.getApprove().getStatus() == status){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_EDIT, "Can't edit because your Account wait Edit!");
        }
        EditWorker editWorker = new EditWorker(worker.getVerifyPic(), worker.getFirstName(),
                worker.getMiddleName(), worker.getLastName(), worker.getPhone(),worker);
        repoEditWorker.save(editWorker);
        repoAccount.save(account);
    }

    @PutMapping("/admin/youCanEdit")
    public void youCanEdit(@RequestParam(name = "idWorker") long idWorker){
        Status status = repoStatus.findById(4L).orElse(null);
        Worker oldWorker = repoWorker.findById(idWorker).orElse(null);
        long idEditWorker = repoEditWorker.getMaxIdWorkerByWorker(oldWorker);
        EditWorker editWorker = repoEditWorker.findById(idEditWorker).orElse(null);
        Account account = repoAccount.findById(oldWorker.getAccount().getIdAccount()).orElse(null);

        Worker newWorker = repoWorker.findById(idWorker).orElse(null);

        newWorker.setVerifyPic(editWorker.getVerifyPic());
        newWorker.setFirstName(editWorker.getFirstName());
        newWorker.setMiddleName(editWorker.getMiddleName());
        newWorker.setLastName(editWorker.getLastName());
        newWorker.setPhone(editWorker.getPhone());
        repoWorker.save(newWorker);

        editWorker.setVerifyPic(oldWorker.getVerifyPic());
        editWorker.setFirstName(oldWorker.getFirstName());
        editWorker.setMiddleName(oldWorker.getMiddleName());
        editWorker.setLastName(oldWorker.getLastName());
        editWorker.setPhone(oldWorker.getPhone());
        repoEditWorker.save(editWorker);

        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }

    @PutMapping("/admin/youCanNotEdit")
    public void youCanNotEdit(@RequestParam(name = "idWorker") long idWorker){
        Status status = repoStatus.findById(4L).orElse(null);
        Worker worker = repoWorker.findById(idWorker).orElse(null);
        long idEditWorker = repoEditWorker.getMaxIdWorkerByWorker(worker);
        Account account = repoAccount.findById(worker.getAccount().getIdAccount()).orElse(null);
        repoEditWorker.deleteById(idEditWorker);
        account.getApprove().setStatus(status);
        repoAccount.save(account);
    }
}
