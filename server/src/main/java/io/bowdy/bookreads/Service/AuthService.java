package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.DTO.LoginRequestDTO;
import io.bowdy.bookreads.DTO.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO);

    ResponseEntity<?> login(LoginRequestDTO loginRequestDTO);
}
