package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Review Id")
    private Long id;
    @Column(name = "Review", nullable = true)
    private String reviews;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Place Id")
    private TouristPlaces touristPlaces;


}
