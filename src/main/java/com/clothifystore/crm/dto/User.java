package com.clothifystore.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
