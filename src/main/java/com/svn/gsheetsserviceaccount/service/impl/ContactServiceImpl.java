package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.repositories.ContactRepository;
import com.svn.gsheetsserviceaccount.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public Contact save(final Contact contact) {
        Assert.notNull(contact,"Сведения о контакте на должны быть null");

        contactRepository.save(contact);

        return contactRepository.save(contact);
    }
}
