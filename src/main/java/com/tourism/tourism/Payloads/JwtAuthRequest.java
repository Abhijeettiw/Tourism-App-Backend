package com.tourism.tourism.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userEmail;
    private String password;
}
