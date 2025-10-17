package net.engineeringdigest.journalApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Schema(description = "Users username")
    private String userName;
    private String email;
    private boolean sentimentanalysis;
    @NotEmpty
    private String password;
}
