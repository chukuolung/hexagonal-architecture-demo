package com.example.ddd.domain.model.user;

import com.example.ddd.domain.event.UserCreated;
import com.example.ddd.domain.event.UserEmailChanged;
import com.example.ddd.domain.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 使用者聚合根實體
 * 遵循 DDD 規則：實體必須有唯一識別碼，包含業務邏輯，避免貧血模型
 */
public class User {
    private UserId id;
    private Email email;
    private String name;
    private boolean active;
    private int version;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * 建立新使用者
     * @param email 電子郵件
     * @param name 姓名
     * @return 新的使用者實例
     */
    public static User create(Email email, String name) {
        User user = new User();
        user.id = UserId.generate();
        user.email = email;
        user.name = name;
        user.active = true;
        user.version = 1;
        
        // 發布領域事件
        user.domainEvents.add(new UserCreated(user.id, user.email, user.name));
        
        return user;
    }

    /**
     * 變更電子郵件
     * @param newEmail 新的電子郵件
     */
    public void changeEmail(Email newEmail) {
        if (!this.active) {
            throw new IllegalStateException("無法變更已停用使用者的電子郵件");
        }
        
        Email oldEmail = this.email;
        this.email = newEmail;
        this.version++;
        
        // 發布領域事件
        this.domainEvents.add(new UserEmailChanged(this.id, oldEmail, newEmail));
    }

    /**
     * 停用使用者
     */
    public void deactivate() {
        if (!this.active) {
            throw new IllegalStateException("使用者已經停用");
        }
        
        this.active = false;
        this.version++;
    }

    /**
     * 啟用使用者
     */
    public void activate() {
        if (this.active) {
            throw new IllegalStateException("使用者已經啟用");
        }
        
        this.active = true;
        this.version++;
    }

    /**
     * 變更姓名
     * @param newName 新的姓名
     */
    public void changeName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("姓名不能為空");
        }
        
        this.name = newName.trim();
        this.version++;
    }

    // Getters
    public UserId getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public int getVersion() {
        return version;
    }

    /**
     * 取得並清除領域事件
     * @return 領域事件列表
     */
    public List<DomainEvent> getDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(this.domainEvents);
        this.domainEvents.clear();
        return events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", version=" + version +
                '}';
    }
} 