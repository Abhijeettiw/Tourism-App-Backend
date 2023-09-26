package com.tourism.tourism.Controller;

import com.tourism.tourism.Configs.JwtService;
import com.tourism.tourism.Entities.User;
import com.tourism.tourism.Payloads.JwtAuthRequest;
import com.tourism.tourism.Payloads.JwtAuthResponse;
import com.tourism.tourism.Payloads.UserDto;
import com.tourism.tourism.Repositories.UserRepo;
import com.tourism.tourism.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServices userServices;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(),request.getPassword()));
        UserDetails userDetails=this.userRepo.getUserByUsername(request.getUserEmail());
        var token=this.jwtService.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> newUser(@Validated @RequestBody UserDto userDto)
    {
        UserDto userDto1=this.userServices.addUser(userDto);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.CREATED);
    }
}
