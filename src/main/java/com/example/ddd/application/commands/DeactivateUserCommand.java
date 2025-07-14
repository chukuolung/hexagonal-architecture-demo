package com.example.ddd.application.commands;

import com.example.ddd.domain.model.user.UserId;

/**
 * 停用使用者命令
 */
public class DeactivateUserCommand {
    private final UserId userId;

    public DeactivateUserCommand(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DeactivateUserCommand{" +
                "userId=" + userId +
                '}';
    }
} 