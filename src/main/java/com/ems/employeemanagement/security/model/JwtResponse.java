package com.ems.employeemanagement.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class JwtResponse {
    private String jwtToken;
    private String username;
}
