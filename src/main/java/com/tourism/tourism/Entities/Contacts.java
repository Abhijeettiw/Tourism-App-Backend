package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Contacted")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Contact Id")
    private Long id;
    @Column(name = "Name",nullable = false)
    private String name;
    @Column(name = "Contact No",nullable = false)
    private Long phoneNo;
    @Column(name = "Email Id",nullable = false)
    private String email;
    @Column(name = "Reason For Contact",nullable = false)
    private String reason;
}
