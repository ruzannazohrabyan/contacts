package com.teams.contacts.repository;

import com.teams.contacts.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Contact findContactByEmail(String email);
}
