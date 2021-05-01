package com.toanlt.springJWT.user.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        // todo regex email
        return true;
    }
}
