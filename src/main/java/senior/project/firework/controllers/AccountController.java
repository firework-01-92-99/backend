package senior.project.firework.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import senior.project.firework.models.Account;
import org.springframework.web.bind.annotation.GetMapping;
import senior.project.firework.repositories.repoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;

    @GetMapping("/admin/allAccount")
    public List<Account> allAccount(){
        return repoAccount.findAll();
    }

    @GetMapping("/admin/selectAccount")
    public Optional<Account> selectAccount(@RequestParam(name = "idAccount") long idAccount){
        return repoAccount.findById(idAccount);
    }

    @PostMapping("/main/register")
    public void RegisAccount(){

    }


}
