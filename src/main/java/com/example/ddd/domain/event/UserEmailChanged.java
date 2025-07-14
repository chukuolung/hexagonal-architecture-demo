package com.example.ddd.domain.event;

import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.UserId;

import java.time.LocalDateTime;

/**
 * 使用者電子郵件變更事件
 * 遵循 DDD 規則：事件代表過去發生的事情，使用過去式命名
 */
public class UserEmailChanged implements DomainEvent {
    private final UserId userId;
    private final Email oldEmail;
    private final Email newEmail;
    private final LocalDateTime occurredAt;

    public UserEmailChanged(UserId userId, Email oldEmail, Email newEmail) {
        this.userId = userId;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.occurredAt = LocalDateTime.now();
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getOldEmail() {
        return oldEmail;
    }

    public Email getNewEmail() {
        return newEmail;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String toString() {
        return "UserEmailChanged{" +
                "userId=" + userId +
                ", oldEmail=" + oldEmail +
                ", newEmail=" + newEmail +
                ", occurredAt=" + occurredAt +
                '}';
    }
} 