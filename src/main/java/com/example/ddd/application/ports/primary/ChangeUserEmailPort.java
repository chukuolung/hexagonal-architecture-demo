package com.example.ddd.application.ports.primary;

import com.example.ddd.application.commands.ChangeUserEmailCommand;

/**
 * 變更使用者電子郵件主要端口
 */
public interface ChangeUserEmailPort {
    
    /**
     * 執行變更使用者電子郵件用例
     * @param command 變更使用者電子郵件命令
     */
    void execute(ChangeUserEmailCommand command);
} 