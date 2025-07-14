package com.example.ddd.infrastructure.adapters.secondary.messaging;

import com.example.ddd.application.ports.secondary.EventPublisherPort;
import com.example.ddd.domain.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 記憶體中的事件發布適配器
 * 主要用於測試和開發環境
 */
@Component
@Primary
public class InMemoryEventPublisher implements EventPublisherPort {
    
    private static final Logger logger = LoggerFactory.getLogger(InMemoryEventPublisher.class);
    private final List<DomainEvent> publishedEvents = new ArrayList<>();

    @Override
    public void publish(DomainEvent event) {
        logger.info("發布事件: {}", event);
        publishedEvents.add(event);
    }

    /**
     * 取得已發布的事件列表
     * @return 事件列表
     */
    public List<DomainEvent> getPublishedEvents() {
        return new ArrayList<>(publishedEvents);
    }

    /**
     * 清除已發布的事件
     */
    public void clearEvents() {
        publishedEvents.clear();
    }
} 