package com.svn.gsheetsserviceaccount.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.web.context.request.RequestAttributes;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GSheetsRequestScopeAttrTests {

    private GSheetsRequestScopeAttr requestScopeAttr;
    final String attributeName = "newAttribute";
    final Object object = new Object();
    final int scopeRequest = RequestAttributes.SCOPE_REQUEST;
    final int scopeSession = RequestAttributes.SCOPE_SESSION;

    @BeforeEach
    void setUp() {
        requestScopeAttr = new GSheetsRequestScopeAttr();
    }

    @Test
    @DisplayName("1. Testing the set & get attribute in GSheetsRequestScopeAttr. Ok.")
    @Order(1)
    void setAndGetRequestScopeAttrTestOk() {

        requestScopeAttr.setAttribute(attributeName, object, RequestAttributes.SCOPE_REQUEST);
        Object objectFromRequestScopeAttr = requestScopeAttr.getAttribute(attributeName, scopeRequest);

        assertNotNull(objectFromRequestScopeAttr);
        assertEquals(object, objectFromRequestScopeAttr);
    }

    @Test
    @DisplayName("2. Testing the setting bad attribute in GSheetsRequestScopeAttr. Bad.")
    @Order(2)
    void setRequestScopeAttrTestBad() {
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> requestScopeAttr.setAttribute(attributeName, object, scopeSession)
        );

        assertEquals("Attribute name: " + attributeName + "; The scope= " + scopeSession + " not supported.",
                iae.getMessage());
    }

    @Test
    @DisplayName("3. Testing the remove attribute from GSheetsRequestScopeAttr. Ok.")
    @Order(3)
    void removeAttributeTestOk() {

        requestScopeAttr.setAttribute(attributeName, object, scopeRequest);
        Object objectFromRequestScopeAttr = requestScopeAttr.getAttribute(attributeName, scopeRequest);
        assertNotNull(objectFromRequestScopeAttr);

        requestScopeAttr.removeAttribute(attributeName, scopeRequest);
        objectFromRequestScopeAttr = requestScopeAttr.getAttribute(attributeName, scopeRequest);
        assertNull(objectFromRequestScopeAttr);
    }

    @Test
    @DisplayName("4. Testing the remove attribute from GSheetsRequestScopeAttr. Bad.")
    @Order(4)
    void removeAttributeTestBad() {

        requestScopeAttr.setAttribute(attributeName, object, scopeRequest);
        Object objectFromRequestScopeAttr = requestScopeAttr.getAttribute(attributeName, scopeRequest);
        assertNotNull(objectFromRequestScopeAttr);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> requestScopeAttr.removeAttribute(attributeName, scopeSession)
        );

        assertEquals("Attribute name: " + attributeName + "; The scope= " + scopeSession + " not supported.",
                iae.getMessage());
    }

    @Test
    @DisplayName("5. Testing the getting attribute names from GSheetsRequestScopeAttr by scope. Ok.")
    @Order(5)
    void getAttributeNamesTestOk() {
        requestScopeAttr.setAttribute(attributeName, object, scopeRequest);

        String[] attributeName = requestScopeAttr.getAttributeNames(scopeRequest);

        assertAll(
                () -> assertNotNull(attributeName),
                () -> assertTrue(attributeName.length > 0)
        );
    }
}
