package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Component
public class UserDTO {

    @NotNull(message = "Enter your OrganisationId")
    private Integer orgId;

    @NotNull (message = "Enter your RoleId")
    private Integer roleId;

    @NotBlank(message = "Enter your EmployeeCode")
    private String empCode;

    @NotBlank(message = "Enter your Name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special characters not allowed!")
    private String empName;

    @NotBlank(message = "Enter your Username")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special characters not allowed!")
    @Size(min = 3,max = 20,message = "Your username must be in 3 to 20 characters and should not have special characters")
    private String username;

    @NotBlank(message = "Enter your Email")
    @Pattern(regexp = "^(.+)+@(.+)$", message = "Enter your appropriate Email!")
    private String email;

    @NotBlank(message = "Enter your Password")
    @Size(min = 6,max = 8,message = "Your Password must be in 6 to 8 characters")
    private String password;

    private Timestamp registrationDate;

    private Status userStatus;

    private Integer createdBy;

    private Timestamp createdDate;

    private Integer updatedBy;

    private Timestamp updatedDate;

}
