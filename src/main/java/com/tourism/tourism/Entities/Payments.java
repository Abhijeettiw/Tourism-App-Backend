package com.tourism.tourism.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Payment Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payments
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment-ID")
    private Long paymentId;
    @Column(name = "Date")
    private Date date;
    @Column(name = "Amount Paid")
    private Double amount;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "User-ID")
    private User user;
}
