# DDD Hexagonal Architecture 專案

這是一個基於 **Domain Driven Design (DDD)** 和 **Hexagonal Architecture (六角形架構)** 的 Java 後端專案實作。

## 📋 專案概述

本專案展示了如何將 DDD 和 Hexagonal Architecture 的設計原則應用於 Java 後端開發，提供了一個完整的使用者管理系統實作範例。

## 🏗️ 架構特色

### Domain Driven Design (DDD)
- **領域模型 (Domain Model)**: 豐富的領域物件，包含業務邏輯
- **聚合根 (Aggregate Root)**: User 作為聚合根，管理內部實體
- **值物件 (Value Objects)**: Email, UserId 等不可變的值物件
- **領域事件 (Domain Events)**: UserCreated, UserEmailChanged 等事件
- **領域例外 (Domain Exceptions)**: 自定義的業務例外

### Hexagonal Architecture (六角形架構)
- **主要端口 (Primary Ports)**: 定義應用程式用例介面
- **次要端口 (Secondary Ports)**: 定義外部系統介面
- **主要適配器 (Primary Adapters)**: Web 控制器等入口點
- **次要適配器 (Secondary Adapters)**: 資料庫、外部服務等實作

## 📁 專案結構

```
src/main/java/com/example/ddd/
├── domain/                          # 領域層
│   ├── model/                       # 領域模型
│   │   └── user/                    # 使用者聚合
│   │       ├── User.java           # 聚合根
│   │       ├── UserId.java         # 值物件
│   │       └── Email.java          # 值物件
│   ├── ports/                       # 領域端口
│   │   └── UserRepository.java     # 儲存庫介面
│   ├── events/                      # 領域事件
│   │   ├── DomainEvent.java        # 事件基礎介面
│   │   ├── UserCreated.java        # 使用者建立事件
│   │   └── UserEmailChanged.java   # 電子郵件變更事件
│   └── exception/                   # 領域例外
│       ├── DomainException.java     # 基礎例外
│       ├── UserNotFoundException.java
│       └── UserAlreadyExistsException.java
├── application/                     # 應用層
│   ├── ports/                       # 應用端口
│   │   ├── primary/                 # 主要端口 (用例)
│   │   │   ├── CreateUserPort.java
│   │   │   ├── ChangeUserEmailPort.java
│   │   │   ├── GetUserPort.java
│   │   │   └── DeactivateUserPort.java
│   │   └── secondary/               # 次要端口 (基礎設施)
│   │       ├── EmailNotificationPort.java
│   │       └── EventPublisherPort.java
│   ├── use_cases/                   # 用例實作
│   │   ├── CreateUserUseCase.java
│   │   ├── ChangeUserEmailUseCase.java
│   │   ├── GetUserUseCase.java
│   │   └── DeactivateUserUseCase.java
│   ├── commands/                    # 命令物件
│   │   ├── CreateUserCommand.java
│   │   ├── ChangeUserEmailCommand.java
│   │   └── DeactivateUserCommand.java
│   ├── queries/                     # 查詢物件
│   │   └── GetUserQuery.java
│   └── responses/                   # 回應物件
│       ├── CreateUserResponse.java
│       └── GetUserResponse.java
├── infrastructure/                   # 基礎設施層
│   └── adapters/                    # 適配器
│       ├── primary/                 # 主要適配器
│       │   └── web/                 # Web 適配器
│       │       └── UserController.java
│       └── secondary/               # 次要適配器
│           ├── sql/                 # SQL 適配器
│           │   ├── SqlUserRepository.java
│           │   ├── UserJpaRepository.java
│           │   └── models/
│           │       └── UserModel.java
│           ├── memory/              # 記憶體適配器
│           │   └── InMemoryUserRepository.java
│           ├── http/                # HTTP 適配器
│           │   └── HttpEmailNotificationAdapter.java
│           └── messaging/           # 訊息適配器
│               └── InMemoryEventPublisher.java
└── DddHexagonalApplication.java     # 主應用程式
```

## 🚀 快速開始

### 前置需求
- Java 17+
- Maven 3.6+

### 建置專案
```bash
mvn clean install
```

### 執行應用程式
```bash
mvn spring-boot:run
```

### 測試 API
應用程式啟動後，可以透過以下端點測試：

#### 建立使用者
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "name": "John Doe"
  }'
```

#### 取得使用者
```bash
curl http://localhost:8080/api/users/{userId}
```

#### 變更使用者電子郵件
```bash
curl -X PATCH http://localhost:8080/api/users/{userId}/email \
  -H "Content-Type: application/json" \
  -d '{
    "email": "new.email@example.com"
  }'
```

#### 停用使用者
```bash
curl -X DELETE http://localhost:8080/api/users/{userId}
```

## 🧪 測試

### 執行單元測試
```bash
mvn test
```

### 執行整合測試
```bash
mvn test -Dspring.profiles.active=test
```

## 📚 設計原則

### DDD 核心概念

1. **實體 (Entity)**
   - 具有唯一識別碼
   - 包含業務邏輯
   - 基於識別碼進行相等性比較

2. **值物件 (Value Object)**
   - 不可變物件
   - 基於所有屬性進行相等性比較
   - 包含驗證邏輯

3. **聚合根 (Aggregate Root)**
   - 管理聚合內部的實體
   - 確保聚合的一致性邊界
   - 發布領域事件

4. **領域事件 (Domain Events)**
   - 代表過去發生的事情
   - 使用過去式命名
   - 包含所有必要的資料

### Hexagonal Architecture 原則

1. **端口 (Ports)**
   - 主要端口：定義應用程式用例
   - 次要端口：定義外部系統介面

2. **適配器 (Adapters)**
   - 主要適配器：翻譯外部請求為領域命令
   - 次要適配器：實作外部系統介面

3. **依賴反轉**
   - 領域層不依賴基礎設施
   - 應用層依賴抽象而非具體實作

## 🔧 技術棧

- **Java 17**: 主要程式語言
- **Spring Boot 3.2**: 應用程式框架
- **Spring Data JPA**: 資料存取層
- **H2 Database**: 記憶體資料庫
- **Maven**: 建置工具
- **JUnit 5**: 測試框架

## 📖 API 文件

### 使用者管理 API

| 方法 | 端點 | 描述 |
|------|------|------|
| POST | `/api/users` | 建立新使用者 |
| GET | `/api/users/{userId}` | 取得使用者資訊 |
| PATCH | `/api/users/{userId}/email` | 變更使用者電子郵件 |
| DELETE | `/api/users/{userId}` | 停用使用者 |

### 請求/回應範例

#### 建立使用者
**請求:**
```json
{
  "email": "john.doe@example.com",
  "name": "John Doe"
}
```

**回應:**
```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000"
}
```

#### 取得使用者
**回應:**
```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "john.doe@example.com",
  "name": "John Doe",
  "active": true
}
```

## 🎯 最佳實踐

### 程式碼品質
- 遵循 SOLID 原則
- 使用有意義的命名
- 包含適當的註解
- 撰寫完整的測試

### 架構設計
- 清晰的層次分離
- 依賴反轉原則
- 單一職責原則
- 開放封閉原則

### 業務邏輯
- 領域邏輯集中在領域層
- 避免貧血模型
- 使用領域事件進行鬆耦合
- 適當的例外處理

## 🤝 貢獻

歡迎提交 Issue 和 Pull Request 來改善這個專案。

## 📄 授權

本專案採用 MIT 授權條款。

## 📞 聯絡資訊

如有任何問題或建議，請透過以下方式聯絡：
- 建立 GitHub Issue
- 發送 Pull Request

---

**注意**: 這是一個教學範例專案，展示了 DDD 和 Hexagonal Architecture 的最佳實踐。在實際生產環境中使用時，請根據具體需求進行適當的調整和擴展。 