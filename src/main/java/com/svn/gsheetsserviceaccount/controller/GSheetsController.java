package com.svn.gsheetsserviceaccount.controller;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.repositories.ContactRepository;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class GSheetsController {

    private final GoogleSheetsService sheetsService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private DataTransferService dataTransferService;

    @Autowired
    public GSheetsController(GoogleConnectionService connectionService,
                             GoogleSheetsService sheetsService) {
        this.sheetsService = sheetsService;
    }

    @GetMapping({"/", "/api/sheet"})
    public ResponseEntity<List<List<String>>> readGoogleSheet(HttpServletResponse response) throws IOException {

        List<List<String>> responseBody = sheetsService.readTable();

        dataTransferService.transfer();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
        //return null;
    }

    @GetMapping("/add")
    public Contact addContact() {

        Contact contact = Contact.create(
                "12345678",
                "Новый контакт",
                "+79146895789",
                "newContact@mail.ru"
        );

        log.info("--- contact: " + System.identityHashCode(contact));
        log.info("--- contact: " + contact);

        Contact insertedContact = contactRepository.insert(contact);
        //insertedContact = contactRepository.save(contact);


        log.info("--- insertedContact: " + System.identityHashCode(insertedContact));
        log.info("--- insertedContact: " + insertedContact);

        return insertedContact;
    }
}
