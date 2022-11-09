package io.bowdy.bookreads.DTO;

import io.bowdy.bookreads.Models.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Set<Role> roles;
}
