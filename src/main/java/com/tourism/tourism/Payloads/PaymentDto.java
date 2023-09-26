package com.tourism.tourism.Payloads;

import com.tourism.tourism.Entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaymentDto
{
    private Long paymentId;
    private Double amount;
    private Date date;
    private UserDto user;

}
