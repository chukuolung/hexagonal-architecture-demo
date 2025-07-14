package com.example.ddd.infrastructure.adapters.primary.web;

import com.example.ddd.application.commands.ChangeUserEmailCommand;
import com.example.ddd.application.commands.CreateUserCommand;
import com.example.ddd.application.commands.DeactivateUserCommand;
import com.example.ddd.application.ports.primary.ChangeUserEmailPort;
import com.example.ddd.application.ports.primary.CreateUserPort;
import com.example.ddd.application.ports.primary.DeactivateUserPort;
import com.example.ddd.application.ports.primary.GetUserPort;
import com.example.ddd.application.queries.GetUserQuery;
import com.example.ddd.application.responses.CreateUserResponse;
import com.example.ddd.application.responses.GetUserResponse;
import com.example.ddd.domain.exception.DomainException;
import com.example.ddd.domain.exception.UserAlreadyExistsException;
import com.example.ddd.domain.exception.UserNotFoundException;
import com.example.ddd.domain.model.user.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用者控制器
 * 遵循 Hexagonal Architecture 規則：主要適配器翻譯外部請求為領域命令/查詢
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final CreateUserPort createUserPort;
    private final ChangeUserEmailPort changeUserEmailPort;
    private final GetUserPort getUserPort;
    private final DeactivateUserPort deactivateUserPort;

    public UserController(
            CreateUserPort createUserPort,
            ChangeUserEmailPort changeUserEmailPort,
            GetUserPort getUserPort,
            DeactivateUserPort deactivateUserPort) {
        this.createUserPort = createUserPort;
        this.changeUserEmailPort = changeUserEmailPort;
        this.getUserPort = getUserPort;
        this.deactivateUserPort = deactivateUserPort;
    }

    /**
     * 建立使用者
     */
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            CreateUserCommand command = new CreateUserCommand(request.getEmail(), request.getName());
            CreateUserResponse response = createUserPort.execute(command);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(null);
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    /**
     * 取得使用者
     */
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") String userId) {
        try {
            GetUserQuery query = new GetUserQuery(UserId.of(userId));
            GetUserResponse response = getUserPort.execute(query);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * 變更使用者電子郵件
     */
    @PatchMapping("/{userId}/email")
    public ResponseEntity<Void> changeUserEmail(
            @PathVariable("userId") String userId,
            @RequestBody ChangeEmailRequest request) {
        try {
            ChangeUserEmailCommand command = new ChangeUserEmailCommand(
                    UserId.of(userId), request.getEmail());
            changeUserEmailPort.execute(command);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * 停用使用者
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("userId") String userId) {
        try {
            DeactivateUserCommand command = new DeactivateUserCommand(UserId.of(userId));
            deactivateUserPort.execute(command);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DomainException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * 建立使用者請求 DTO
     */
    public static class CreateUserRequest {
        private String email;
        private String name;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 變更電子郵件請求 DTO
     */
    public static class ChangeEmailRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
} 