package com.example.ddd.domain.event;

import java.time.LocalDateTime;

/**
 * 領域事件基礎介面
 * 所有領域事件都應該實作此介面
 */
public interface DomainEvent {
    
    /**
     * 取得事件發生的時間
     * @return 事件發生時間
     */
    LocalDateTime getOccurredAt();
} 