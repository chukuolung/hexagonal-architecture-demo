package com.example.ddd.application.use_cases;

import com.example.ddd.application.commands.CreateUserCommand;
import com.example.ddd.application.ports.primary.CreateUserPort;
import com.example.ddd.application.ports.secondary.EmailNotificationPort;
import com.example.ddd.application.ports.secondary.EventPublisherPort;
import com.example.ddd.application.responses.CreateUserResponse;
import com.example.ddd.domain.event.UserCreated;
import com.example.ddd.domain.exception.UserAlreadyExistsException;
import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.ports.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import com.example.ddd.domain.event.DomainEvent;

/**
 * 建立使用者用例
 * 遵循 DDD 規則：用例代表單一業務操作，協調領域物件但不包含業務邏輯
 */
@Component
public class CreateUserUseCase implements CreateUserPort {
    
    private final UserRepository userRepository;
    private final EmailNotificationPort emailNotificationPort;
    private final EventPublisherPort eventPublisherPort;

    public CreateUserUseCase(
            UserRepository userRepository,
            EmailNotificationPort emailNotificationPort,
            EventPublisherPort eventPublisherPort) {
        this.userRepository = userRepository;
        this.emailNotificationPort = emailNotificationPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public CreateUserResponse execute(CreateUserCommand command) {
        // 建立領域物件
        Email email = new Email(command.getEmail());
        
        // 檢查電子郵件是否已存在
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException(email);
        }
        
        // 建立使用者聚合根
        User user = User.create(email, command.getName());
        
        // 儲存使用者
        userRepository.save(user);
        
        // 發布領域事件
        List<DomainEvent> domainEvents = user.getDomainEvents();
        for (DomainEvent event : domainEvents) {
            eventPublisherPort.publish(event);
        }
        
        // 發送歡迎電子郵件
        emailNotificationPort.sendWelcomeEmail(email, command.getName());
        
        return new CreateUserResponse(user.getId().getValue());
    }
} 