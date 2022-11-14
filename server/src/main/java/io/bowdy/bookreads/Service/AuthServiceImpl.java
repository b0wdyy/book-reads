package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.DTO.JwtResponseDTO;
import io.bowdy.bookreads.DTO.LoginRequestDTO;
import io.bowdy.bookreads.DTO.RegisterRequestDTO;
import io.bowdy.bookreads.DTO.SuccessResponseDTO;
import io.bowdy.bookreads.Enums.ERole;
import io.bowdy.bookreads.Models.Role;
import io.bowdy.bookreads.Models.User;
import io.bowdy.bookreads.Repositories.RoleRepository;
import io.bowdy.bookreads.Repositories.UserRepository;
import io.bowdy.bookreads.Util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> register(RegisterRequestDTO registerRequest) {
        if (this.userRepository.existsByUsername(registerRequest.getUsername())) {
            return this.response(HttpStatus.BAD_REQUEST, "Username already taken", false);
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(this.passwordEncoder.encode(registerRequest.getPassword()));

        Optional<Role> roles = this.roleRepository.findByName(ERole.ROLE_ADMIN);
        if (roles.isEmpty()) {
            return this.response(HttpStatus.BAD_REQUEST, "Role not found", false);
        }
        user.setRoles(Collections.singleton(roles.get()));
        this.userRepository.save(user);

        return this.response(HttpStatus.CREATED, "User created", true);
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDTO loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = this.jwtUtil.generateToken(authentication);
        Optional<User> optionalUser = this.userRepository.findByUsername(this.jwtUtil.getUsernameFromToken(token));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            JwtResponseDTO response = new JwtResponseDTO();
            response.setUsername(user.getUsername());
            response.setToken(token);
            response.setRoles(user.getRoles());
            return ResponseEntity.ok(response);
        }

        return this.response(HttpStatus.BAD_REQUEST, "User not found", false);
    }

    private ResponseEntity<?> response(HttpStatus status, String message, Boolean success) {
        return ResponseEntity
                .status(status)
                .body(SuccessResponseDTO
                        .builder()
                        .message(message)
                        .success(success)
                        .build()
                );
    }
}
