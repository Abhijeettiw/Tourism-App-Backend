package com.tourism.tourism.Payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ContactsDto {
    private Long id;
    private String name;
    private Long phoneNo;
    private String email;
    private String reason;
}
