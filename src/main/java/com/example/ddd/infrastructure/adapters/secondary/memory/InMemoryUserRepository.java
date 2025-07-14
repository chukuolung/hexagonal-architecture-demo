package com.example.ddd.infrastructure.adapters.secondary.memory;

import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.model.user.UserId;
import com.example.ddd.domain.ports.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 記憶體中的使用者儲存庫適配器
 * 主要用於測試和開發環境
 */
@Repository
@Primary
public class InMemoryUserRepository implements UserRepository {
    
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByEmail(Email email) {
        return Optional.ofNullable(usersByEmail.get(email.getValue()));
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.ofNullable(users.get(userId.getValue()));
    }

    @Override
    public void save(User user) {
        String userId = user.getId().getValue();
        String email = user.getEmail().getValue();
        
        users.put(userId, user);
        usersByEmail.put(email, user);
    }

    @Override
    public void delete(UserId userId) {
        User user = users.remove(userId.getValue());
        if (user != null) {
            usersByEmail.remove(user.getEmail().getValue());
        }
    }

    @Override
    public List<User> findAllActive() {
        return users.values()
                .stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return usersByEmail.containsKey(email.getValue());
    }
} 