package com.onlinetaskmanagementsystem.OTMS.Service;


import com.onlinetaskmanagementsystem.OTMS.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    Integer addUser(UserDTO userDTO);
}
