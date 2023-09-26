package com.tourism.tourism.Controller;

import com.tourism.tourism.Exception.InvalidException;
import com.tourism.tourism.Payloads.UserDto;
import com.tourism.tourism.Services.UserServices;
import com.tourism.tourism.Utilities.ApiResponse;
import com.tourism.tourism.Utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserServices userServices;

    @PostMapping("/new")
    public ResponseEntity<UserDto> newUser(@Validated @RequestBody UserDto userDto)
    {
        UserDto userDto1=this.userServices.addUser(userDto);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.CREATED);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateuser(@Validated @RequestBody UserDto userDto,@PathVariable("userId") Long userId)
    {
        this.userServices.updateUser(userDto,userId);
        return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId)
    {
        UserDto userDto=this.userServices.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.FOUND);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NO,required = false) Integer pageNo,
                                                @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                @RequestParam(value = "sortId",defaultValue = "userId",required = false) String sortBy)
    {
        return  ResponseEntity.ok(this.userServices.getAllUser(pageNo,pageSize,sortBy));
    }

    @DeleteMapping("/del/userId/{userId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("userId") Long userId)
    {
        this.userServices.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);
    }
    @PutMapping("email/{email}/setPass")
    public ResponseEntity<UserDto> newPass(@RequestBody UserDto userDto,@PathVariable("email") String email)
    {
        UserDto userDto1=this.userServices.changePassword(userDto,email);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

}
