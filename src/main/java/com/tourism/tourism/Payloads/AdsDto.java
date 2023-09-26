package com.tourism.tourism.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {
    private Long id;
    private String name;
    private String imageName;
}
