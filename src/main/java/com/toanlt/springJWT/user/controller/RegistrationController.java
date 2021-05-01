package com.toanlt.springJWT.user.controller;

import com.toanlt.springJWT.common.APIRoutes;
import com.toanlt.springJWT.user.model.RegistrationRequest;
import com.toanlt.springJWT.user.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@RestController
@RequestMapping(path= APIRoutes.API_PATH + APIRoutes.API_URL.REGISTRATION)
@AllArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request, HttpServletRequest httpServletRequest) throws MalformedURLException {
        return userRegistrationService.register(request, httpServletRequest);
    };

    @GetMapping(path = APIRoutes.API_URL.CONFIRM_TOKEN)
    public String confirmToken(@RequestParam("token") String token){
        return userRegistrationService.confirmationToken(token);
    }
}
