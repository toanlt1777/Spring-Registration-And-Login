package com.toanlt.springJWT.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {
    private String lastName;
    private String firstName;
    private String email;
    private String username;
    private String password;
}
