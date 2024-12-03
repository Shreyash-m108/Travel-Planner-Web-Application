package com.travelplanner.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}