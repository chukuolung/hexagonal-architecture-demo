package com.example.ddd.infrastructure.adapters.secondary.http;

import com.example.ddd.application.ports.secondary.EmailNotificationPort;
import com.example.ddd.domain.model.user.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * HTTP 電子郵件通知適配器
 * 遵循 Hexagonal Architecture 規則：次要適配器實作次要端口
 */
@Component
public class HttpEmailNotificationAdapter implements EmailNotificationPort {
    
    private static final Logger logger = LoggerFactory.getLogger(HttpEmailNotificationAdapter.class);

    @Override
    public void sendWelcomeEmail(Email userEmail, String userName) {
        logger.info("發送歡迎電子郵件給: {} ({})", userEmail.getValue(), userName);
        // 實際實作會呼叫外部電子郵件服務 API
        // 這裡簡化為記錄日誌
    }

    @Override
    public void sendEmailChangeNotification(Email oldEmail, Email newEmail) {
        logger.info("發送電子郵件變更通知: {} -> {}", oldEmail.getValue(), newEmail.getValue());
        // 實際實作會呼叫外部電子郵件服務 API
        // 這裡簡化為記錄日誌
    }
} 