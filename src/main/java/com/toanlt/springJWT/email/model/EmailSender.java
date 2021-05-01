package com.toanlt.springJWT.email.model;

public interface EmailSender{
    void send(String to, String email);
}
