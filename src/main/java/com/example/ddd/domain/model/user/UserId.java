package com.example.ddd.domain.model.user;

import lombok.Value;

import java.util.UUID;

/**
 * 使用者識別碼值物件
 * 遵循 DDD 規則：值物件必須是不可變的，基於所有屬性進行相等性比較
 */
@Value
public class UserId {
    String value;

    /**
     * 建立新的使用者識別碼
     * @param value 識別碼字串值
     */
    public UserId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("使用者識別碼不能為空");
        }
        this.value = value;
    }

    /**
     * 生成新的使用者識別碼
     * @return 新的 UserId 實例
     */
    public static UserId generate() {
        return new UserId(UUID.randomUUID().toString());
    }

    /**
     * 從字串建立 UserId
     * @param value 識別碼字串
     * @return UserId 實例
     */
    public static UserId of(String value) {
        return new UserId(value);
    }

    @Override
    public String toString() {
        return value;
    }
} 