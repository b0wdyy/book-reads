package io.bowdy.bookreads.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessResponseDTO {
    private String message;
    private Boolean success;
}
