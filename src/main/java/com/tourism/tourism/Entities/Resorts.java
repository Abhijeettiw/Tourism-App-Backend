package com.tourism.tourism.Entities;

import com.tourism.tourism.Utilities.Role;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Resorts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resorts {

    @Id
    @Column(name = "Resort Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resortId;
    @Column(name = "Resort Name",nullable = false)
    private String name;
    @Column(name = "Owner", nullable = false)
    private String owner;
    @Column(name = "Contact No",nullable = false)
    private Long contact;
    @Column(name = "Email",nullable = false)
    private String email;
    @Column(name = "Address",nullable = false)
    private String address;
    @Column(name = "Image Name",nullable = false)
    private String imageName;
    @Column(name = "Password",nullable = false)
    private String password;
    @Column(name = "Featured")
    private Boolean featured;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Tourist Site Id")
    private TouristPlaces touristPlaces;
    @OneToMany(mappedBy = "resorts",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<ResortReviews> ResortReviews=new ArrayList<>();
    @OneToMany(mappedBy = "resorts", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<ResortNearbyPlaces> resortNearbyPlaces=new ArrayList<>();
    @OneToMany(mappedBy = "resorts",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ResortFeatures> resortFeatures=new ArrayList<>();

}
