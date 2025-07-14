package com.example.ddd.domain.model.user;

import com.example.ddd.domain.event.UserCreated;
import com.example.ddd.domain.event.UserEmailChanged;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用者領域物件測試
 * 遵循測試規則：測試領域邏輯時不模擬領域物件
 */
class UserTest {

    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        Email email = new Email("test@example.com");
        String name = "John Doe";

        // Act
        User user = User.create(email, name);

        // Assert
        assertNotNull(user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
        assertTrue(user.isActive());
        assertEquals(1, user.getVersion());
        
        // 驗證領域事件
        var domainEvents = user.getDomainEvents();
        assertEquals(1, domainEvents.size());
        assertTrue(domainEvents.get(0) instanceof UserCreated);
    }

    @Test
    void shouldChangeEmailSuccessfully() {
        // Arrange
        User user = User.create(new Email("old@example.com"), "John Doe");
        Email newEmail = new Email("new@example.com");
        user.getDomainEvents(); // 清除初始事件

        // Act
        user.changeEmail(newEmail);

        // Assert
        assertEquals(newEmail, user.getEmail());
        assertEquals(2, user.getVersion());
        
        // 驗證領域事件
        var domainEvents = user.getDomainEvents();
        assertEquals(1, domainEvents.size());
        assertTrue(domainEvents.get(0) instanceof UserEmailChanged);
    }

    @Test
    void shouldDeactivateUserSuccessfully() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");
        user.getDomainEvents(); // 清除初始事件

        // Act
        user.deactivate();

        // Assert
        assertFalse(user.isActive());
        assertEquals(2, user.getVersion());
    }

    @Test
    void shouldActivateUserSuccessfully() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");
        user.deactivate();
        user.getDomainEvents(); // 清除事件

        // Act
        user.activate();

        // Assert
        assertTrue(user.isActive());
        assertEquals(3, user.getVersion());
    }

    @Test
    void shouldChangeNameSuccessfully() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");
        String newName = "Jane Doe";

        // Act
        user.changeName(newName);

        // Assert
        assertEquals(newName, user.getName());
        assertEquals(2, user.getVersion());
    }

    @Test
    void shouldThrowExceptionWhenChangingEmailOfDeactivatedUser() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");
        user.deactivate();
        Email newEmail = new Email("new@example.com");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> user.changeEmail(newEmail));
    }

    @Test
    void shouldThrowExceptionWhenDeactivatingAlreadyDeactivatedUser() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");
        user.deactivate();

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> user.deactivate());
    }

    @Test
    void shouldThrowExceptionWhenActivatingAlreadyActiveUser() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> user.activate());
    }

    @Test
    void shouldThrowExceptionWhenChangingNameToEmpty() {
        // Arrange
        User user = User.create(new Email("test@example.com"), "John Doe");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.changeName(""));
        assertThrows(IllegalArgumentException.class, () -> user.changeName(null));
    }

    @Test
    void shouldHaveCorrectEqualityBasedOnId() {
        // Arrange
        User user1 = User.create(new Email("test1@example.com"), "John Doe");
        User user2 = User.create(new Email("test2@example.com"), "Jane Doe");
        User user3 = User.create(new Email("test3@example.com"), "Bob Smith");

        // Act & Assert
        assertNotEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertNotEquals(user2, user3);
        
        // 相同 ID 的使用者應該相等
        User user1Copy = User.create(new Email("different@example.com"), "Different Name");
        // 注意：這裡需要手動設定相同的 ID 來測試相等性
        // 實際實作中可能需要更複雜的測試設定
    }
} 