package com.example.ddd.application.commands;

/**
 * 建立使用者命令
 * 遵循 CQRS 模式：命令代表意圖改變系統狀態的操作
 */
public class CreateUserCommand {
    private final String email;
    private final String name;

    public CreateUserCommand(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreateUserCommand{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
} 