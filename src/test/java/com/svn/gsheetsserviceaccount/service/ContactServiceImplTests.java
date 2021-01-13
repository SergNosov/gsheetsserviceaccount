package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.repositories.ContactRepository;
import com.svn.gsheetsserviceaccount.service.impl.ContactServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTests {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    private Contact contact;

    @BeforeEach
    void setUp(){
        contact = new Contact(ObjectId.get(),"12345","NewContact","91234344","post@mail.kp");
    }

    @Test
    @DisplayName("1. Testing the save contact. Ok.")
    @Order(1)
    void testSaveContactOk(){
        given(contactRepository.save(any(Contact.class))).willReturn(contact);
        Contact savedContact = contactService.save(contact);

        assertNotNull(savedContact);
    }

    @Test
    @DisplayName("2. Testing the save null contact.")
    @Order(2)
    void testSaveContactIsNull(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> contactService.save(null)
        );

        assertEquals("Сведения о контакте на должны быть null", iae.getMessage());
        then(contactRepository).should(times(0)).save(any(Contact.class));
    }
}
