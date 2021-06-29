package com.teams.contacts.service;

import com.teams.contacts.model.Contact;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);

    List<Contact> getAll();

    Contact getByEmail(String email);

    void deleteByEmail(String email);

    Contact updateByEmail(String email, Contact contact);
}
