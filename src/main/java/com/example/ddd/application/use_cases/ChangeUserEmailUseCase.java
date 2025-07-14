package com.example.ddd.application.use_cases;

import com.example.ddd.application.commands.ChangeUserEmailCommand;
import com.example.ddd.application.ports.primary.ChangeUserEmailPort;
import com.example.ddd.application.ports.secondary.EmailNotificationPort;
import com.example.ddd.application.ports.secondary.EventPublisherPort;
import com.example.ddd.domain.exception.UserNotFoundException;
import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.model.user.UserId;
import com.example.ddd.domain.ports.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import com.example.ddd.domain.event.DomainEvent;

/**
 * 變更使用者電子郵件用例
 */
@Component
public class ChangeUserEmailUseCase implements ChangeUserEmailPort {
    
    private final UserRepository userRepository;
    private final EmailNotificationPort emailNotificationPort;
    private final EventPublisherPort eventPublisherPort;

    public ChangeUserEmailUseCase(
            UserRepository userRepository,
            EmailNotificationPort emailNotificationPort,
            EventPublisherPort eventPublisherPort) {
        this.userRepository = userRepository;
        this.emailNotificationPort = emailNotificationPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(ChangeUserEmailCommand command) {
        // 尋找使用者
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        
        // 建立新的電子郵件值物件
        Email newEmail = new Email(command.getNewEmail());
        
        // 檢查新電子郵件是否已存在
        if (userRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("電子郵件已存在: " + newEmail);
        }
        
        // 變更電子郵件（業務邏輯在領域物件中）
        user.changeEmail(newEmail);
        
        // 儲存使用者
        userRepository.save(user);
        
        // 發布領域事件
        List<DomainEvent> domainEvents = user.getDomainEvents();
        for (DomainEvent event : domainEvents) {
            eventPublisherPort.publish(event);
        }
        
        // 發送電子郵件變更通知
        emailNotificationPort.sendEmailChangeNotification(user.getEmail(), newEmail);
    }
} 