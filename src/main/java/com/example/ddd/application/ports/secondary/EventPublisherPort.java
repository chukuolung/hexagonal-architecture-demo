package com.example.ddd.application.ports.secondary;

import com.example.ddd.domain.event.DomainEvent;

/**
 * 事件發布次要端口
 */
public interface EventPublisherPort {
    
    /**
     * 發布領域事件
     * @param event 要發布的領域事件
     */
    void publish(DomainEvent event);
} 