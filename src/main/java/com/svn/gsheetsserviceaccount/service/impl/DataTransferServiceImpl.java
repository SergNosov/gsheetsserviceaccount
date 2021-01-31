package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.service.ContactService;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DataTransferServiceImpl implements DataTransferService {

    private final GoogleSheetsService googleSheetsService;
    private final ContactService contactService;

    public DataTransferServiceImpl(GoogleSheetsService googleSheetsService,
                                   ContactService contactService) {
        this.googleSheetsService = googleSheetsService;
        this.contactService = contactService;
    }

    @Scheduled(fixedDelay = 20000)
    @Override
    public void transfer() {

        final List<List<String>> googleTableValues = googleSheetsService.readTable();
        final List<Contact> contacts = createContactList(googleTableValues);

        saveAllContacts(contacts);
    }

    private List<Contact> createContactList(List<List<String>> values) {
        List<Contact> contacts = new ArrayList<>();

        for (List<String> recordValues : values) {
            try {
                Contact contact = Contact.create(recordValues);
                if (!contactService.existsByCode(contact.getCode())) {
                    contacts.add(contact);
                }
            } catch (IllegalArgumentException iae) {
                log.warn("--- не создан контакт: " + recordValues + "; " + iae.getMessage());
            }
        }
        printContacts(contacts);
        return List.copyOf(contacts);
    }

    private void printContacts(List<Contact> contacts) {
        if (contacts.size() > 0) {
            contacts.stream().forEach(c -> log.info("--- contact: " + c));
        } else {
            log.warn("--- Список контактов пуст.");
        }
    }

    private void saveAllContacts(List<Contact> contacts) {
        if (contacts.size() > 0) {//todo перейти к индивидуальному сохранению contact и при успехе генерировать pdf
            List<Contact> savedList = contactService.saveAll(contacts);
            log.info("--- Количество сохраненных контактов: " + savedList.size());
        } else {
            log.warn("--- Список контактов пуст.");
        }
    }
}
