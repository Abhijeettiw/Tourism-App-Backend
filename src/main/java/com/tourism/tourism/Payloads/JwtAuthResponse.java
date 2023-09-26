package com.tourism.tourism.Payloads;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthResponse {
    private String token;
}
