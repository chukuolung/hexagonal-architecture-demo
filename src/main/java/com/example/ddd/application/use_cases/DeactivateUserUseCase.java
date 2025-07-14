package com.example.ddd.application.use_cases;

import com.example.ddd.application.commands.DeactivateUserCommand;
import com.example.ddd.application.ports.primary.DeactivateUserPort;
import com.example.ddd.application.ports.secondary.EventPublisherPort;
import com.example.ddd.domain.exception.UserNotFoundException;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.ports.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import com.example.ddd.domain.event.DomainEvent;

/**
 * 停用使用者用例
 */
@Component
public class DeactivateUserUseCase implements DeactivateUserPort {
    
    private final UserRepository userRepository;
    private final EventPublisherPort eventPublisherPort;

    public DeactivateUserUseCase(
            UserRepository userRepository,
            EventPublisherPort eventPublisherPort) {
        this.userRepository = userRepository;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public void execute(DeactivateUserCommand command) {
        // 尋找使用者
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        
        // 停用使用者（業務邏輯在領域物件中）
        user.deactivate();
        
        // 儲存使用者
        userRepository.save(user);
        
        // 發布領域事件
        List<DomainEvent> domainEvents = user.getDomainEvents();
        for (DomainEvent event : domainEvents) {
            eventPublisherPort.publish(event);
        }
    }
} 