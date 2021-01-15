package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;

public interface ContactService {

    public Contact save(final Contact contact);
    public boolean existsByCode(String code);
}
