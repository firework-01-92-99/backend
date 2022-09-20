package senior.project.firework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import senior.project.firework.repositories.repoAccount;
import senior.project.firework.models.Account;
import senior.project.firework.models.AuthenticationUser;

import java.util.Map;
import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    repoAccount repoAccount ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Optional<Account> user = Optional.ofNullable(repoAccount.findByAccUsername(username));
//        return user.map(AuthenticationUser::new).get();

        Account user = repoAccount.findByEmail(username);
        return new AuthenticationUser(user);

    }
}

