package com.tourism.tourism.Services;

import com.tourism.tourism.Payloads.ContactsDto;

import java.util.List;

public interface ContactServices {
    ContactsDto addContact(ContactsDto contactsDto);
    Void deleteContact(Long contactId);
    List<ContactsDto> allContacted();
}
