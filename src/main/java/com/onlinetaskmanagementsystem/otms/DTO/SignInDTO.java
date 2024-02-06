package com.onlinetaskmanagementsystem.otms.DTO;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInDTO {
    @NotBlank(message = "Enter your Email")
    @Pattern(regexp = "^(.+)+@(.+)$", message = "Enter your appropriate Email!")
    private  String email;
    @NotBlank(message="Enter your password")
    private  String password;




}
