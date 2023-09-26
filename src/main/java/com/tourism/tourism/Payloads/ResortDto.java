package com.tourism.tourism.Payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResortDto {
    private Long resortId;
    private String owner;
    private String name;
    private Long contact;
    private String address;
    private String imageName="Default.png";
    private Boolean featured;
    private TouristDto touristPlaces;
}
