package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Resort reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResortReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Resort Review Id")
    private Long id;
    @Column(name = "Resort Review", nullable = true)
    private String reviews;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "Resort Id")
    private Resorts resorts;
}
