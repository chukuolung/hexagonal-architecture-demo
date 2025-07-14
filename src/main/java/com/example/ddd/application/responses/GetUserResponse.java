package com.example.ddd.application.responses;

/**
 * 取得使用者回應
 */
public class GetUserResponse {
    private final String userId;
    private final String email;
    private final String name;
    private final boolean active;

    public GetUserResponse(String userId, String email, String name, boolean active) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.active = active;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "GetUserResponse{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
} 