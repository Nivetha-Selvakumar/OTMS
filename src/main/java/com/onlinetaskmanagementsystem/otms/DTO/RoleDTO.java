package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Component
public class RoleDTO {

    @NotBlank(message = "Enter the role")
    private String role;

    private ActiveStatus activeStatus;
}
