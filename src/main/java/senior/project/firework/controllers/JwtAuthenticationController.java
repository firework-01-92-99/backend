package senior.project.firework.controllers;

import java.util.Objects;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.Account;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.services.JWTUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import senior.project.firework.repositories.repoAccount;


import senior.project.firework.config.JwtTokenUtil;
import senior.project.firework.models.JwtResponse;
import senior.project.firework.models.JwtRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JWTUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private repoAccount repoAccount;

    @RequestMapping(value = "/main/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        if(repoAccount.findByEmail(authenticationRequest.getUsername()) == null) {
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_EMAIL_INCORRECT,"Email incorrect!!");
        }
        Account account = repoAccount.findByEmail(authenticationRequest.getUsername());
        if(!passwordEncoder.matches(authenticationRequest.getPassword(),account.getPassword())){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_PASSWORD_INCORRECT,"Password incorrect!!");
        }
        if(account.getApprove().getStatus().getIdStatus() != 1 || account.getApprove().getStatus().getIdStatus() != 4
                || account.getApprove().getStatus().getIdStatus() != 10){
            checkStatusAccount(account.getApprove().getStatus());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void checkStatusAccount(Status status) throws Exception {
        if (status.getIdStatus() == 5) {
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_REJECT, "Account Reject!!");
        } else if (status.getIdStatus() == 9) {
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_DELETED, "Account Deleted!!");
        } else if(status.getIdStatus() == 6){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_APPROVE,"Status Wait Approve!!");
        } else if(status.getIdStatus() == 10){
            throw new AccountException(ExceptionRepo.ERROR_CODE.STATUS_ACCOUNT_WAIT_OTP,"Status Wait OTP!!");
        }
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

    @PostMapping(value = "/main/passwordencoder")
    public String testPasswordEncoder(@RequestParam(defaultValue = "" , name = "password") String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }
}
