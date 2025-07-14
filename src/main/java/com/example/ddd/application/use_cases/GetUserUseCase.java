package com.example.ddd.application.use_cases;

import com.example.ddd.application.ports.primary.GetUserPort;
import com.example.ddd.application.queries.GetUserQuery;
import com.example.ddd.application.responses.GetUserResponse;
import com.example.ddd.domain.exception.UserNotFoundException;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.ports.UserRepository;
import org.springframework.stereotype.Component;

/**
 * 取得使用者用例
 */
@Component
public class GetUserUseCase implements GetUserPort {
    
    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResponse execute(GetUserQuery query) {
        // 尋找使用者
        User user = userRepository.findById(query.getUserId())
                .orElseThrow(() -> new UserNotFoundException(query.getUserId()));
        
        // 建立回應 DTO
        return new GetUserResponse(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getName(),
                user.isActive()
        );
    }
} 