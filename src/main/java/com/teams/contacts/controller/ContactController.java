package com.teams.contacts.controller;

import com.teams.contacts.model.Contact;
import com.teams.contacts.service.impl.ContactServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact/")
public class ContactController {
    private final ContactServiceImpl contactServiceImpl;

    public ContactController(ContactServiceImpl contactServiceImpl) {
        this.contactServiceImpl = contactServiceImpl;
    }

    @PostMapping
    public Contact save(@RequestBody Contact contact) {
        return contactServiceImpl.save(contact);
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactServiceImpl.getAll();
    }
}
