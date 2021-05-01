package com.toanlt.springJWT.user.service;

import com.toanlt.springJWT.user.model.AppUser;
import com.toanlt.springJWT.user.model.ConfirmationToken;
import com.toanlt.springJWT.user.reponsitory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private  static final String USER_NOT_FOUND_MSG = "can not found user %s from server";

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s).
                orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, s)));
    }

    public Optional<AppUser> loadUserByUserName(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    public String signUp(AppUser appUser) {
        Optional<AppUser> isExist = userRepository.findUserByEmail(appUser.getEmail());
        if(isExist.isPresent()){
            throw new IllegalStateException("User already taken");
        }
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        userRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
