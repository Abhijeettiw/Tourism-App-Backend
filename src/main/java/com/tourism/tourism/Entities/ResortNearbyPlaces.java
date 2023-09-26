package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Nearby Places")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResortNearbyPlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Place Id" )
    private Long id;
    @Column(name = "Name",nullable = false)
    private String name;
    @Column(name = "Image Name",nullable = false)
    private String imageName;
    @Column(name = "Description",nullable = false)
    private String description;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Resort Id")
    private Resorts resorts;

}
