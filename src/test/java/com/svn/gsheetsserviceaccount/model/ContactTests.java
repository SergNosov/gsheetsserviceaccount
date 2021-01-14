package com.svn.gsheetsserviceaccount.model;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

public class ContactTests {

    @Test
    void createTest(){

        Contact contact = new Contact(ObjectId.get(),"123","213","123","234");
    }
}
