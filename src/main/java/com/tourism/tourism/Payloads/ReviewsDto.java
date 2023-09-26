package com.tourism.tourism.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReviewsDto {
    private Long id;
    private String reviews;
    private TouristDto touristPlaces;
}
