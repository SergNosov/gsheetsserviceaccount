package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {

    public Optional<Contact> save(final Contact contact);
    public boolean existsByCode(String code);
    List<Contact> saveAll(List<Contact> contacts);
}
