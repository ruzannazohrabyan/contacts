package com.teams.contacts.service.impl;

import com.teams.contacts.model.Contact;
import com.teams.contacts.repository.ContactRepository;
import com.teams.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public Contact save(Contact contact) {
        if(!isEmailValidated(contact.getEmail()))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid Email Format");

        if (isEmailExists(contact.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Exists");

        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAll() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public Contact getByEmail(String email) {
        if(!isEmailValidated(email))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid Email Format");

        if (!isEmailExists(email))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email does not exists");
        return contactRepository.findContactByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        if(!isEmailValidated(email))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid Email Format");
        if (isEmailExists(email)) {
            contactRepository.deleteContactsByEmail(email);
            throw new ResponseStatusException(HttpStatus.OK, "Contact is deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email does not exists");
    }

    @Override
    public Contact updateByEmail(String email, Contact contact) {
        if(!isEmailValidated(email))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid Email Format");
        if (!isEmailExists(email))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email does not exists");

        Contact newContact = getByEmail(email);
        newContact.setEmail(contact.getEmail());
        contactRepository.save(newContact);
        return newContact;
    }

    private boolean isEmailExists(String email) {
        Contact contact = contactRepository.findContactByEmail(email);
        return contact != null;
    }

    private boolean isEmailValidated(String email) {
        String regex = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
