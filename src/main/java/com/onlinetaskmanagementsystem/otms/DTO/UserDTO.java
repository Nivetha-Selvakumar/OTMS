package com.onlinetaskmanagementsystem.otms.DTO;

import com.onlinetaskmanagementsystem.otms.Enum.ActiveStatus;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Enter your EmployeeCode")
    private String empCode;

    @NotBlank(message = "Enter your Name")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special characters not allowed!")
    private String empName;

    private Integer roleId;

    @NotBlank(message = "Enter your Username")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Special characters not allowed!")
    @Size(min = 3,max = 20,message = "Your username must be in 3 to 20 characters and should not have special characters")
    private String username;

    @NotBlank(message = "Enter your Email")
    @Pattern(regexp = "^(.+)+@(.+)$", message = "Enter your appropriate Email!")
    private String email;

    @NotBlank(message = "Enter your Password")
    @Size(min = 6,max = 8,message = "Your Password must be in 6 to 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=()-])(?=\\S+$).{6,8}$",message = "Enter valid password. Your password must contain atleast one capital case, one small case, one number and one special character and must be between 6-8 characters")
    private String password;

    private Timestamp registrationDate;

    private ActiveStatus userStatus;


}
