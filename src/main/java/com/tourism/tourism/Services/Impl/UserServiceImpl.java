package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Configs.JwtService;
import com.tourism.tourism.Entities.User;
import com.tourism.tourism.Exception.InvalidException;
import com.tourism.tourism.Exception.ResourceByEmailNotFoundException;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.JwtAuthResponse;
import com.tourism.tourism.Payloads.UserDto;
import com.tourism.tourism.Repositories.UserRepo;
import com.tourism.tourism.Services.UserServices;
import com.tourism.tourism.Utilities.ApiResponse;
import com.tourism.tourism.Utilities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserDto addUser(UserDto userDto) {
        User newUser = this.modelMapper.map(userDto, User.class);
        newUser.setRole(Role.USER);
        this.userRepo.save(newUser);
        String token=jwtService.generateToken(newUser);
        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        user.setName(userDto.getName());
        user.setNo(userDto.getNo());
        user.setEmail(userDto.getEmail());
        this.userRepo.save(user);
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable p= PageRequest.of(pageNo,pageSize, Sort.by(sortBy).descending());
        Page<User> userPage=this.userRepo.findAll(p);
       List<User> all=userPage.getContent();
       List<UserDto> allDto=all.stream().map((user)->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
       return allDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        this.userRepo.deleteById(userId);
    }

    @Override
    public UserDto changePassword(UserDto userDto,String email) {
        User user= this.userRepo.getUserByUsername(userDto.getEmail());
        user.getPassword();
        this.userRepo.save(user);
        return null;
    }


}

