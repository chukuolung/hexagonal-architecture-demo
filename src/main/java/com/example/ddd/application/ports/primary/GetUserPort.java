package com.example.ddd.application.ports.primary;

import com.example.ddd.application.queries.GetUserQuery;
import com.example.ddd.application.responses.GetUserResponse;

/**
 * 取得使用者主要端口
 */
public interface GetUserPort {
    
    /**
     * 執行取得使用者用例
     * @param query 取得使用者查詢
     * @return 取得使用者回應
     */
    GetUserResponse execute(GetUserQuery query);
} 