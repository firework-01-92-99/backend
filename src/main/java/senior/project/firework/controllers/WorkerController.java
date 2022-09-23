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

    @GetMapping("/main/allWorker")
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


    @PostMapping("/main/editMyWorker")
    public void editMyWorker(@RequestBody Worker worker){
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

    @PutMapping("/main/youCanEdit")
    public void youCanEdit(@RequestParam(name = "idWorker") long idWorker){
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

    }

    @DeleteMapping("/main/youCanNotEdit")
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
