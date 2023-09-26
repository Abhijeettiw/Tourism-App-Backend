package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.ContactsDto;
import com.tourism.tourism.Services.ContactServices;
import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/home/admin/contacted")
public class ContactController {
    @Autowired
    private ContactServices contactServices;
    @PostMapping("/new")
    public ResponseEntity<ContactsDto> newContact(@RequestBody ContactsDto contactsDto)
    {
        ContactsDto contactsDto1=this.contactServices.addContact(contactsDto);
        return new ResponseEntity<>(contactsDto1, HttpStatus.OK);
    }
    @DeleteMapping("/conId/{contactId}/del")
    public ResponseEntity<ApiResponse> delContact(@PathVariable("contactId") Long contactId)
    {
        this.contactServices.deleteContact(contactId);
        return new ResponseEntity<>(new ApiResponse("Deleted",true,HttpStatus.OK),HttpStatus.OK);
    }
    @GetMapping("/allContacted")
    public ResponseEntity<List<ContactsDto>> allContacted()
    {
        List<ContactsDto> contactsDtoList=this.contactServices.allContacted();
        return new ResponseEntity<>(contactsDtoList,HttpStatus.FOUND);
    }
}
