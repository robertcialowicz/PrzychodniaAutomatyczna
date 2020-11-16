package com.iet.przychodnia.user_authentication_system.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private String username;
    private String password;
}
