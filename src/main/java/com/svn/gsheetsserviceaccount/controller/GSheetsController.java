package com.svn.gsheetsserviceaccount.controller;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.model.UserCard;
import com.svn.gsheetsserviceaccount.repositories.UserCardRepository;
import com.svn.gsheetsserviceaccount.service.ContactService;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import com.svn.gsheetsserviceaccount.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GSheetsController {

    private final GoogleSheetsService sheetsService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private DataTransferService dataTransferService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private UserCardRepository userCardRepository;

    public GSheetsController(GoogleSheetsService sheetsService) {
        this.sheetsService = sheetsService;
    }

    @GetMapping({"/"})
    public String readGoogleSheet() {
        dataTransferService.transfer();
        return "dataTransferService complete";
    }

    /**
     * используется для тестирования сервиса
     * @return
     */

    @GetMapping("/add")
    public UserCard addContact() {

        Contact contact = Contact.create(
                "42",
                "Новый контакт",
                "+79146895789",
                "newContact@mail.ru"
        );

        log.info("--- contact: " + System.identityHashCode(contact));
        log.info("--- contact: " + contact);

        Contact insertedContact = contactService.save(contact)
                .orElseThrow(() -> new IllegalArgumentException("--- В базе данных есть контакт с code:" + contact.getCode()));

        byte[] pdfByte = pdfService.generatePdf(insertedContact);

        UserCard userCard = new UserCard(insertedContact, new Binary(pdfByte));

        userCard = userCardRepository.save(userCard);


        log.info("--- insertedContact: " + System.identityHashCode(insertedContact));
        log.info("--- insertedContact: " + insertedContact);

        log.info("--- create UserCard object: " + userCard);

        return userCard;
    }
}
