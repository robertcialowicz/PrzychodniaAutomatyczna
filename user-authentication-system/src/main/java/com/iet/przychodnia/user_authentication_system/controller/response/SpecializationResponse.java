package com.iet.przychodnia.user_authentication_system.controller.response;

import lombok.Value;

import java.util.UUID;

@Value
public class SpecializationResponse {
   UUID id;
   String name;
}
