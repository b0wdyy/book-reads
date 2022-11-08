package io.bowdy.bookreads.Service;

import io.bowdy.bookreads.DTO.JwtResponseDTO;
import io.bowdy.bookreads.DTO.LoginRequestDTO;
import io.bowdy.bookreads.DTO.RegisterRequestDTO;
import io.bowdy.bookreads.Enums.ERole;
import io.bowdy.bookreads.Models.Role;
import io.bowdy.bookreads.Models.User;
import io.bowdy.bookreads.Repositories.RoleRepository;
import io.bowdy.bookreads.Repositories.UserRepository;
import io.bowdy.bookreads.Util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public JwtResponseDTO loginUser(LoginRequestDTO loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtResponseDTO response = new JwtResponseDTO();
        response.setToken(jwt);
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRoles(roles);

        return response;
    }

    public void registerUser(RegisterRequestDTO registerRequest) throws Exception {
        if (this.userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new Exception("Email already taken");
        }

        if (this.userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new Exception("Username already taken");
        }
        String hashedPassword = this.passwordEncoder.encode(registerRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = this.roleRepository.findByName(ERole.ROLE_USER);
        if (userRole.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER);
            Role savedRole = this.roleRepository.save(role);
            roles.add(savedRole);
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(hashedPassword);
        user.setRoles(roles);
        this.userRepository.save(user);
    }
}
