package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.Contacts;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.ContactsDto;
import com.tourism.tourism.Repositories.ContactsRepo;
import com.tourism.tourism.Services.ContactServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ContactServicesImpl implements ContactServices {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ContactsRepo contactsRepo;
    @Override
    public ContactsDto addContact(ContactsDto contactsDto) {
        Contacts contacts=this.modelMapper.map(contactsDto,Contacts.class);
        this.contactsRepo.save(contacts);
        return this.modelMapper.map(contacts,ContactsDto.class);
    }

    @Override
    public Void deleteContact(Long contactId) {
        Contacts contacts=this.contactsRepo.findById(contactId).orElseThrow(()->new ResourceNotFoundException("name",contactId));
        this.contactsRepo.delete(contacts);
        return null;
    }

    @Override
    public List<ContactsDto> allContacted() {
        List<Contacts> contactsList=this.contactsRepo.findAll();
        List<ContactsDto> contactsDtoList=contactsList.stream().map((c)->this.modelMapper.map(c,ContactsDto.class)).collect(Collectors.toList());
        return contactsDtoList;
    }
}
