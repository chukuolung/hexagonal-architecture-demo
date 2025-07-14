package com.example.ddd.domain.ports;

import com.example.ddd.domain.model.user.Email;
import com.example.ddd.domain.model.user.User;
import com.example.ddd.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

/**
 * 使用者儲存庫介面
 * 遵循 DDD 規則：儲存庫介面定義在領域層，使用領域語言，只處理聚合根
 */
public interface UserRepository {
    
    /**
     * 根據電子郵件尋找使用者
     * @param email 電子郵件
     * @return 使用者，如果找不到則返回空
     */
    Optional<User> findByEmail(Email email);
    
    /**
     * 根據識別碼尋找使用者
     * @param userId 使用者識別碼
     * @return 使用者，如果找不到則返回空
     */
    Optional<User> findById(UserId userId);
    
    /**
     * 儲存使用者
     * @param user 要儲存的使用者
     */
    void save(User user);
    
    /**
     * 刪除使用者
     * @param userId 要刪除的使用者識別碼
     */
    void delete(UserId userId);
    
    /**
     * 尋找所有啟用的使用者
     * @return 啟用使用者列表
     */
    List<User> findAllActive();
    
    /**
     * 檢查電子郵件是否已存在
     * @param email 要檢查的電子郵件
     * @return 如果存在則返回 true
     */
    boolean existsByEmail(Email email);
} 