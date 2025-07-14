package com.example.ddd.domain.exception;

import com.example.ddd.domain.model.user.UserId;

/**
 * 使用者找不到例外
 */
public class UserNotFoundException extends DomainException {
    
    private final UserId userId;
    
    public UserNotFoundException(UserId userId) {
        super("找不到使用者: " + userId);
        this.userId = userId;
    }
    
    public UserId getUserId() {
        return userId;
    }
} 