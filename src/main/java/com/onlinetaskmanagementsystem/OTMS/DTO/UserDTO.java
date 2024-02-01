package com.onlinetaskmanagementsystem.OTMS.DTO;


import com.onlinetaskmanagementsystem.OTMS.Enum.Status;
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

    @NotNull(message = "Enter your Organisation Id")
    private Integer orgId;
    @NotNull (message = "Enter your Role Id")
    private Integer roleId;
    @NotBlank(message = "Enter your Employee Code")
    private String empCode;
    @NotBlank(message = "Enter your Name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special Characters not allowed!")
    private String empName;
    @NotBlank(message = "Enter your user name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special Characters not allowed!")
    @Size(min = 3,max = 20,message = "Your username must be in 3 to 20 characters and should not have special characters")
    private String username;
    @NotBlank(message = "Enter your email")
    @Pattern(regexp = "^(.+)+@(.+)$", message = "Enter Your Appropriate email!")
    private String email;
    @NotBlank(message = "Enter your password")
    @Size(min = 8,max = 20,message = "Your username must be in 8 to 20 characters")
    private String password;
//    @NotNull
    private Timestamp registrationDate;
//    @NotBlank(message = "Active or Inactive user should be mentioned")
    private Status userStatus;
//    @NotNull
    private Integer createdBy;
//    @NotNull
    private Timestamp createdDate;
//    @NotNull
    private Integer updatedBy;
//    @NotNull
    private Timestamp updatedDate;

    /*@Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", roleId=" + roleId +
                ", empCode=" + empCode +
                ", empName='" + empName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", userStatus='" + userStatus + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                '}';
    }*/

}
