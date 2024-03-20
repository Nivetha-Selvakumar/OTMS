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
public class OrganisationDTO {

    @NotBlank(message = "Enter the Organisation reference")
    private String orgRef;

    @NotBlank(message = "Enter Organisation name")
    private String orgName;

    @NotBlank(message = "Enter the Address")
    private String address;

    private ActiveStatus activeStatus;
}
