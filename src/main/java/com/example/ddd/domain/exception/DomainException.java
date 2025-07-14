package com.example.ddd.domain.exception;

/**
 * 領域例外基礎類別
 * 所有領域相關的例外都應該繼承此類別
 */
public abstract class DomainException extends RuntimeException {
    
    public DomainException(String message) {
        super(message);
    }
    
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
} 