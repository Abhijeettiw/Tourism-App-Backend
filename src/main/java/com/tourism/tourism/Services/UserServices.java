package com.tourism.tourism.Services;

import com.tourism.tourism.Exception.InvalidException;
import com.tourism.tourism.Payloads.UserDto;
import com.tourism.tourism.Utilities.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface UserServices
{
    public UserDto addUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,Long userId);
    public UserDto getUserById(Long UserId);
    public List<UserDto> getAllUser(Integer pageNo,Integer pageSize,String sortBy);
    public void deleteUser(Long userId);
    public UserDto changePassword(UserDto userDto,String email);

}
