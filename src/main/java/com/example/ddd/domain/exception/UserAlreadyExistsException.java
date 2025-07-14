package com.example.ddd.domain.exception;

import com.example.ddd.domain.model.user.Email;

/**
 * 使用者已存在例外
 */
public class UserAlreadyExistsException extends DomainException {
    
    private final Email email;
    
    public UserAlreadyExistsException(Email email) {
        super("使用者已存在: " + email);
        this.email = email;
    }
    
    public Email getEmail() {
        return email;
    }
} 