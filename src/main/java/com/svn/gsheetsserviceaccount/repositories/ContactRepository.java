package com.svn.gsheetsserviceaccount.repositories;

import com.svn.gsheetsserviceaccount.model.Contact;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, ObjectId> {

    public boolean existsContactByCode(String code);
}
