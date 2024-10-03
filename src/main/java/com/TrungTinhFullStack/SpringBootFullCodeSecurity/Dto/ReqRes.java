package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRes {

    private Long id;

    private String username;

    private String password;

    private String role;

    private String jwt;

    private Long statusCode;

    private String message;

}
