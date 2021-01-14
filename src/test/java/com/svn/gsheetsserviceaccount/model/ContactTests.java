package com.svn.gsheetsserviceaccount.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactTests {

    private static final List<String> valuesGood = Arrays.asList("12345","NewContact","91234344","post@mail.kp");
    private static final List<String> valuesBadCode = Arrays.asList("","NewContact","91234344","post@mail.kp");
    private static final List<String> valuesBadName = Arrays.asList("12345","","91234344","post@mail.kp");
    private static final List<String> valuesBadPhone = Arrays.asList("12345","NewContact","","post@mail.kp");
    private static final List<String> valuesBadEmail = Arrays.asList("12345","NewContact","91234344","postmail.kp");

    @Test
    @DisplayName("1. Testing the create contact. Ok.")
    @Order(1)
    void createTestOk() {
        String code = valuesGood.get(0);
        String name = valuesGood.get(1);
        String phone = valuesGood.get(2);
        String email = valuesGood.get(3);

        Contact contact = Contact.create(code, name, phone, email);

        assertAll(
                () -> assertNotNull(contact),
                () -> assertEquals(code, contact.getCode()),
                () -> assertEquals(name, contact.getName()),
                () -> assertEquals(phone, contact.getPhone()),
                () -> assertEquals(email, contact.getEmail())
        );

        System.out.println("--- contact:" + contact);
    }

    @Test
    @DisplayName("2. Testing the create contact. Bad email.")
    @Order(2)
    void createTestBadEmail() {
        String code = valuesBadEmail.get(0);
        String name = valuesBadEmail.get(1);
        String phone = valuesBadEmail.get(2);
        String email = valuesBadEmail.get(3);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> Contact.create(code, name, phone, email)
        );
        assertEquals("Проверьте наименование электронной почты: " + email, iae.getMessage());
    }
}
