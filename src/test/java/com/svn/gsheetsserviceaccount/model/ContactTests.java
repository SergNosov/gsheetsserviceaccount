package com.svn.gsheetsserviceaccount.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactTests {

    @Test
    @DisplayName("1. Testing the create contact. Ok.")
    @Order(1)
    void createTestOk() {
        List<String> valuesGood = GoogleSheetValues.VALUES_GOOD.getValues();

        Contact contact = Contact.create(valuesGood);

        assertAll(
                () -> assertNotNull(contact),
                () -> assertEquals(valuesGood.get(0), contact.getCode()),
                () -> assertEquals(valuesGood.get(1), contact.getName()),
                () -> assertEquals(valuesGood.get(2), contact.getPhone()),
                () -> assertEquals(valuesGood.get(3), contact.getEmail())
        );
    }

    @Test
    @DisplayName("2. Testing the create contact. Bad email.")
    @Order(2)
    void createTestBadEmail() {

        List<String> valuesBadEmail = GoogleSheetValues.VALUES_BADEMAIL.getValues();

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> Contact.create(valuesBadEmail)
        );
        assertEquals("Проверьте наименование электронной почты: " + valuesBadEmail.get(3), iae.getMessage());
    }

    @ParameterizedTest
    @EnumSource(value = GoogleSheetValues.class, names = {
            "VALUES_BADCODE",
            "VALUES_BADNAME",
            "VALUES_BADPHONE",
            "VALUES_BADEMAIL"
    })
    @DisplayName("3. Testing the create contact. Bad values.")
    @Order(3)
    void createTestBadAll(GoogleSheetValues googleSheetValues) {
        assertThrows(IllegalArgumentException.class,
                () -> Contact.create(googleSheetValues.getValues())
        );
    }

    @Test
    @DisplayName("4. Testing the create contact. Null.")
    @Order(4)
    void createTestNull(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> Contact.create(null)
        );

        assertEquals("Значение values не должно быть null.", iae.getMessage());
    }

    @Test
    @DisplayName("5. Testing the create contact. Empty list.")
    @Order(5)
    void createTestEmpty(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> Contact.create(new ArrayList<>())
        );

        assertEquals("Значение values не должно быть пустым.", iae.getMessage());
    }
}
