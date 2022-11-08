package io.bowdy.bookreads.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
}
