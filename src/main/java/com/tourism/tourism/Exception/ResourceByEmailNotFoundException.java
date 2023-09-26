package com.tourism.tourism.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceByEmailNotFoundException extends RuntimeException{
    private String email;
    public ResourceByEmailNotFoundException(String name)
    {
        super(String.format("% Not Found !!! %"));
        this.email=name;
    }
}
