package com.toanlt.springJWT.user.controller;

import com.toanlt.springJWT.common.APIRoutes;
import com.toanlt.springJWT.security.JwtTokenProvider;
import com.toanlt.springJWT.user.model.AppUser;
import com.toanlt.springJWT.user.model.LoginRequest;
import com.toanlt.springJWT.user.model.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= APIRoutes.API_PATH + APIRoutes.API_URL.LOGIN)
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        String jwt = jwtTokenProvider.generateToken((AppUser) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
