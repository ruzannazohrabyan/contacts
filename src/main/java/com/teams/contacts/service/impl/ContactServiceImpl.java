package com.teams.contacts.service.impl;

import com.teams.contacts.model.Contact;
import com.teams.contacts.repository.ContactRepository;
import com.teams.contacts.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }




    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAll() {
        return (List<Contact>) contactRepository.findAll();
    }
}
