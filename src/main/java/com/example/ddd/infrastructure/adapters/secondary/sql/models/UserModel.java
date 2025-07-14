package com.example.ddd.infrastructure.adapters.secondary.sql.models;

import jakarta.persistence.*;

/**
 * SQL 使用者模型
 * 遵循 Hexagonal Architecture 規則：技術特定模型保持在適配器實作內
 */
@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    private String id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private boolean active;
    
    @Version
    private int version;

    // 預設建構函式
    public UserModel() {}

    public UserModel(String id, String email, String name, boolean active, int version) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.active = active;
        this.version = version;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", version=" + version +
                '}';
    }
} 