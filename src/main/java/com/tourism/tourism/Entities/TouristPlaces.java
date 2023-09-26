package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Tourist Places")
public class TouristPlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="Place ID",nullable = false)
    private Long touristId;

    @Column(name = "Name",nullable = false)
    private String name;
    @Column(name = "Image Name")
    private String imageName;
    @OneToMany(mappedBy = "touristPlaces",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Reviews> reviewsList=new ArrayList<>();
    @OneToMany(mappedBy = "touristPlaces",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Resorts> resortsList=new ArrayList<>();

}
