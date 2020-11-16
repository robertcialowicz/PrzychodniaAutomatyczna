package com.iet.przychodnia.user_authentication_system.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
@Value
public class JwtResponse {
    String jwtToken;
}
