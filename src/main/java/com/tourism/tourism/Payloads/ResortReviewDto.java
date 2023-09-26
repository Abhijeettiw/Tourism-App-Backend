package com.tourism.tourism.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResortReviewDto {
    private Long id;
    private String reviews;
    private ResortDto resorts;
}
