package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.service.ContactService;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataTransferServiceImpl implements DataTransferService {

    private final GoogleSheetsService googleSheetsService;
    private final ContactService contactService;

    @Override
    public void transfer() {
        try {
            List<List<String>> googleTableValues = googleSheetsService.readTable();
            List<Contact> contacts = new ArrayList<>();

            for (List<String> recordValues : googleTableValues) {
                try {
                    Contact contact = Contact.create(recordValues);
                    if (!contactService.existsByCode(contact.getCode())) {
                        contacts.add(contact);
                    }
                } catch (IllegalArgumentException iae){
                    log.error("--- не создан контакт: "+recordValues+"; "+iae.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("--- dataTransferService.transfer() exception: " + e.getMessage());
        }
    }
}
