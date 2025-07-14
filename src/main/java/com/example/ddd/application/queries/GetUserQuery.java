package com.example.ddd.application.queries;

import com.example.ddd.domain.model.user.UserId;

/**
 * 取得使用者查詢
 * 遵循 CQRS 模式：查詢代表意圖讀取系統狀態的操作
 */
public class GetUserQuery {
    private final UserId userId;

    public GetUserQuery(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetUserQuery{" +
                "userId=" + userId +
                '}';
    }
} 