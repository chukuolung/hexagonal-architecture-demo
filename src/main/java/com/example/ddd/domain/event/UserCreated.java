package com.example.ddd.domain.event;

import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.UserId;

import java.time.LocalDateTime;

/**
 * 使用者建立事件
 * 遵循 DDD 規則：事件代表過去發生的事情，使用過去式命名
 */
public class UserCreated implements DomainEvent {
    private final UserId userId;
    private final Email email;
    private final String name;
    private final LocalDateTime occurredAt;

    public UserCreated(UserId userId, Email email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.occurredAt = LocalDateTime.now();
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String toString() {
        return "UserCreated{" +
                "userId=" + userId +
                ", email=" + email +
                ", name='" + name + '\'' +
                ", occurredAt=" + occurredAt +
                '}';
    }
} 