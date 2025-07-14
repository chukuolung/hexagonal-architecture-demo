package com.example.ddd.infrastructure.adapters.secondary.sql;

import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.model.user.UserId;
import com.example.ddd.domain.ports.UserRepository;
import com.example.ddd.infrastructure.adapters.secondary.sql.models.UserModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SQL 使用者儲存庫適配器
 * 遵循 Hexagonal Architecture 規則：次要適配器實作次要端口
 * 暫時禁用，使用記憶體適配器進行開發
 */
@Repository
@Profile("sql") // 只在 sql profile 啟用時才載入
public class SqlUserRepository implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;

    public SqlUserRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.getValue())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue())
                .map(this::toDomain);
    }

    @Override
    public void save(User user) {
        UserModel userModel = toModel(user);
        userJpaRepository.save(userModel);
    }

    @Override
    public void delete(UserId userId) {
        userJpaRepository.deleteById(userId.getValue());
    }

    @Override
    public List<User> findAllActive() {
        return userJpaRepository.findByActiveTrue()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userJpaRepository.existsByEmail(email.getValue());
    }

    /**
     * 將領域物件轉換為 SQL 模型
     */
    private UserModel toModel(User user) {
        return new UserModel(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getName(),
                user.isActive(),
                user.getVersion()
        );
    }

    /**
     * 將 SQL 模型轉換為領域物件
     */
    private User toDomain(UserModel userModel) {
        User user = new User();
        // 使用反射或建構函式來設定私有欄位
        // 這裡簡化處理，實際實作需要更複雜的映射邏輯
        return user;
    }
} 