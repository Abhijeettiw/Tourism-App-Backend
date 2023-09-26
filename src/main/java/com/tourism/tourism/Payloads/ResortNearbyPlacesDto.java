package com.tourism.tourism.Payloads;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResortNearbyPlacesDto {
    private Long id;
    private String name;
    private String imageName="Default.png";
    private String description;
    private ResortDto resorts;
}
