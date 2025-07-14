package com.example.ddd.domain.model.user;

import lombok.Value;

import java.util.regex.Pattern;

/**
 * 電子郵件值物件
 * 遵循 DDD 規則：值物件必須是不可變的，包含驗證邏輯
 */
@Value
public class Email {
    String value;
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    /**
     * 建立新的電子郵件值物件
     * @param value 電子郵件地址
     * @throws IllegalArgumentException 如果電子郵件格式無效
     */
    public Email(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("電子郵件不能為空");
        }
        
        String trimmedValue = value.trim();
        if (!isValidEmail(trimmedValue)) {
            throw new IllegalArgumentException("無效的電子郵件格式: " + value);
        }
        
        this.value = trimmedValue;
    }

    /**
     * 驗證電子郵件格式
     * @param email 要驗證的電子郵件
     * @return 如果格式有效則返回 true
     */
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 取得電子郵件的網域部分
     * @return 網域名稱
     */
    public String getDomain() {
        return value.split("@")[1];
    }

    /**
     * 取得電子郵件的使用者名稱部分
     * @return 使用者名稱
     */
    public String getUsername() {
        return value.split("@")[0];
    }

    @Override
    public String toString() {
        return value;
    }
} 