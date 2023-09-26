package com.tourism.tourism.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristDto {
    private Long touristId;
    private String name;
    private String imageName;

}
