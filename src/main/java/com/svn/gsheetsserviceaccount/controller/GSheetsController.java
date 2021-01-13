package com.svn.gsheetsserviceaccount.controller;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.repositories.ContactRepository;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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

    private final GoogleConnectionService connectionService;
    private final GoogleSheetsService sheetsService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    public GSheetsController(GoogleConnectionService connectionService,
                             GoogleSheetsService sheetsService) {
        this.connectionService = connectionService;
        this.sheetsService = sheetsService;
    }

    @GetMapping({"/","/api/sheet"})
    public ResponseEntity<List<List<Object>>> readGoogleSheet(HttpServletResponse response) throws IOException {

        List<List<Object>> responseBody = sheetsService.readTable(connectionService);

        return new ResponseEntity<List<List<Object>>>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/add")
    public Contact addContact(){

        Contact contact = new Contact(ObjectId.get(),
                "12345678",
                "Новый контакт",
                "+79146895789",
                "newContact@mail.ru");

        log.info("--- contact: "+System.identityHashCode(contact));
        log.info("--- contact: "+contact);

        Contact insertedContact = contactRepository.insert(contact);
         //insertedContact = contactRepository.save(contact);


        log.info("--- insertedContact: "+System.identityHashCode(insertedContact));
        log.info("--- insertedContact: "+insertedContact);

        return insertedContact;
    }
}
