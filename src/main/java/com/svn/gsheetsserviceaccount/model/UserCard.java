package com.svn.gsheetsserviceaccount.model;

import lombok.Data;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class UserCard {

    @Id
    private final ObjectId id = ObjectId.get();
    private final Contact contact;
    private final Binary pdfCard;
    private boolean sended;
    private LocalDateTime dateOfSending;
}
