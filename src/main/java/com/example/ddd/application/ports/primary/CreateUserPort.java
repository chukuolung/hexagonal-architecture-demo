package com.example.ddd.application.ports.primary;

import com.example.ddd.application.commands.CreateUserCommand;
import com.example.ddd.application.responses.CreateUserResponse;

/**
 * 建立使用者主要端口
 * 遵循 Hexagonal Architecture 規則：主要端口定義應用程式用例
 */
public interface CreateUserPort {
    
    /**
     * 執行建立使用者用例
     * @param command 建立使用者命令
     * @return 建立使用者回應
     */
    CreateUserResponse execute(CreateUserCommand command);
} 