package com.example.ddd.application.ports.primary;

import com.example.ddd.application.commands.DeactivateUserCommand;

/**
 * 停用使用者主要端口
 */
public interface DeactivateUserPort {
    
    /**
     * 執行停用使用者用例
     * @param command 停用使用者命令
     */
    void execute(DeactivateUserCommand command);
} 