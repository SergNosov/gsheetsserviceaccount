package com.svn.gsheetsserviceaccount.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.List;

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

    /**
     * Статический метод create(List<Object> values) используется для
     * созданиея объекта класса Contact из списка объектов(строк)
     * полученных из внешнего источника.
     * @param values список объектов (String) присваиваемых полям класса Contact
     * @return Объект класса Contact c уникальным значением id типа org.bson.types.ObjectId
     * @throws IllegalArgumentException если values = null или values.isEmpty()=true;
     */

    public static Contact create(List<Object> values){
        Assert.notNull(values,"Значение values не должно быть null.");
        Assert.notEmpty(values,"Значение values не должно быть пустым.");



        return null;
    }
}
