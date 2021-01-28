package com.svn.gsheetsserviceaccount.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.web.context.request.RequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GSheetsRequestScopeAttrTests {

    final private GSheetsRequestScopeAttr requestScopeAttr = new GSheetsRequestScopeAttr();
    final String attributeName = "newAttribute";
    final Object object = new Object();

    @Test
    @DisplayName("1. Testing the set & get attribute in GSheetsRequestScopeAttr. Ok.")
    @Order(1)
    void setAndGetRequestScopeAttrTestOk(){

        requestScopeAttr.setAttribute(attributeName,object, RequestAttributes.SCOPE_REQUEST);
        Object objectFromRequestScopeAttr = requestScopeAttr.getAttribute(attributeName,RequestAttributes.SCOPE_REQUEST);

        assertNotNull(objectFromRequestScopeAttr);
        assertEquals(object,objectFromRequestScopeAttr);
    }

    @Test
    @DisplayName("2. Testing the set bad attribute in GSheetsRequestScopeAttr. Bad.")
    @Order(2)
    void setRequestScopeAttrTestBad(){
        int badScope = RequestAttributes.SCOPE_SESSION;

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> requestScopeAttr.setAttribute(attributeName,object, badScope)
        );

        assertEquals("Attribute name: " + attributeName + "; The scope= " + badScope + " not supported.", iae.getMessage());
    }
}
