package io.bowdy.bookreads.Controllers;

import io.bowdy.bookreads.DTO.JwtResponseDTO;
import io.bowdy.bookreads.DTO.LoginRequestDTO;
import io.bowdy.bookreads.DTO.RegisterRequestDTO;
import io.bowdy.bookreads.DTO.SuccessResponseDTO;
import io.bowdy.bookreads.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            JwtResponseDTO jwtResponseDTO = this.userService.loginUser(loginRequest);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            this.userService.registerUser(registerRequest);
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(SuccessResponseDTO.builder().success(true).message("Account created").build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(SuccessResponseDTO.builder().success(false).message(e.getMessage()).build());
        }
    }
}
