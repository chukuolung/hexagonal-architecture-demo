package com.example.ddd.application.commands;

import com.example.ddd.domain.model.user.UserId;

/**
 * 變更使用者電子郵件命令
 */
public class ChangeUserEmailCommand {
    private final UserId userId;
    private final String newEmail;

    public ChangeUserEmailCommand(UserId userId, String newEmail) {
        this.userId = userId;
        this.newEmail = newEmail;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getNewEmail() {
        return newEmail;
    }

    @Override
    public String toString() {
        return "ChangeUserEmailCommand{" +
                "userId=" + userId +
                ", newEmail='" + newEmail + '\'' +
                '}';
    }
} 