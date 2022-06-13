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
        if(repoAccount.findByUsername(authenticationRequest.getUsername()) == null) {
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_USERNAME_INCORRECT,"Username incorrect!!");
        }
        Account account = repoAccount.findByUsername(authenticationRequest.getUsername());
        if(!passwordEncoder.matches(authenticationRequest.getPassword(),account.getPassword())){
            throw new AccountException(ExceptionRepo.ERROR_CODE.ACCOUNT_PASSWORD_INCORRECT,"Password incorrect!!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
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
