package com.example.ddd.application.ports.secondary;

import com.example.ddd.domain.model.user.Email;

/**
 * 電子郵件通知次要端口
 * 遵循 Hexagonal Architecture 規則：次要端口定義基礎設施服務
 */
public interface EmailNotificationPort {
    
    /**
     * 發送歡迎電子郵件
     * @param userEmail 使用者電子郵件
     * @param userName 使用者姓名
     */
    void sendWelcomeEmail(Email userEmail, String userName);
    
    /**
     * 發送電子郵件變更通知
     * @param oldEmail 舊電子郵件
     * @param newEmail 新電子郵件
     */
    void sendEmailChangeNotification(Email oldEmail, Email newEmail);
} 