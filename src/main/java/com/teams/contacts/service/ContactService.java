package com.teams.contacts.service;

import com.teams.contacts.model.Contact;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);
    List<Contact> getAll();

}
