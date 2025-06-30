package com.example.user.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("O email '" + email + "' já está em uso.");
    }
}
