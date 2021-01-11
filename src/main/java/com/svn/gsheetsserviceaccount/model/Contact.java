package com.svn.gsheetsserviceaccount.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter(AccessLevel.NONE)
@Document
public class Contact {

    @Id
    private final ObjectId id;
    private final String code;
    private final String name;
    private final String phone;
    private final String email;
}
