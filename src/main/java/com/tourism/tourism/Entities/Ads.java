package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Ads")
@Data
@AllArgsConstructor
@NotEmpty
@Builder
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ads Id")
    private Long id;
    @Column(name = "Company Name",nullable = false)
    private String name;

    @Column(name = "Image")
    private String imageName;

}
