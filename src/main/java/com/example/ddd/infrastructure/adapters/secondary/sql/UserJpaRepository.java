package com.example.ddd.infrastructure.adapters.secondary.sql;

import com.example.ddd.infrastructure.adapters.secondary.sql.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA 使用者儲存庫介面
 * 遵循 Hexagonal Architecture 規則：技術特定介面保持在適配器層
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserModel, String> {
    
    /**
     * 根據電子郵件尋找使用者
     * @param email 電子郵件
     * @return 使用者模型
     */
    Optional<UserModel> findByEmail(String email);
    
    /**
     * 檢查電子郵件是否存在
     * @param email 電子郵件
     * @return 如果存在則返回 true
     */
    boolean existsByEmail(String email);
    
    /**
     * 尋找所有啟用的使用者
     * @return 啟用使用者列表
     */
    List<UserModel> findByActiveTrue();
} 