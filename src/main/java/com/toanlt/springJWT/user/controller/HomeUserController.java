package com.toanlt.springJWT.user.controller;

import com.toanlt.springJWT.common.APIRoutes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = APIRoutes.API_PATH + APIRoutes.API_URL.HOME_USER)
public class HomeUserController {

    @GetMapping
    public String home(){
        return "hello world";
    }
}
