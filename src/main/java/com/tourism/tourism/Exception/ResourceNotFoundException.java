package com.tourism.tourism.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException
{
    String name;
    Long id;
    String email;
    public ResourceNotFoundException(String name, Long id)
    {
        super(String.format(" Not Found !!! "));
        this.name=name;
        this.id= id;
    }
    public ResourceNotFoundException(String name, String email) {
        super(String.format(" Not Found !!! "));
        this.name = name;
        this.email=email;
    }
}
