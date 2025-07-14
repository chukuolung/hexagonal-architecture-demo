package com.example.ddd.application.responses;

/**
 * 建立使用者回應
 */
public class CreateUserResponse {
    private final String userId;

    public CreateUserResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "userId='" + userId + '\'' +
                '}';
    }
} 