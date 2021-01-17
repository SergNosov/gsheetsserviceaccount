package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;

import java.util.List;

public interface ContactService {

    public Contact save(final Contact contact);
    public boolean existsByCode(String code);
    List<Contact> saveAll(List<Contact> contacts);
}
