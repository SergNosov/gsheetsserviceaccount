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
     * Статический метод create(List<String> values) используется для
     * созданиея объекта класса Contact из списка строк
     * полученных из внешнего источника.
     *
     * @param values список(String) присваиваемых полям класса Contact
     * @return Объект класса Contact c уникальным значением id типа org.bson.types.ObjectId
     * @throws IllegalArgumentException если values = null или values.isEmpty()=true;
     */

    public static Contact create(List<String> values) {
        try {
            Assert.notNull(values, "Значение values не должно быть null.");
            Assert.notEmpty(values, "Значение values не должно быть пустым.");
            Assert.doesNotContain("@", values.get(3), "Неверное значение email: " + values);

            Contact contact = new Contact(
                    ObjectId.get(),
                    values.get(0),
                    values.get(1),
                    values.get(2),
                    values.get(3)
            );
            return contact;
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("--- Навозможно создать контакт из :" + values +
                    ". Message: " + ex.getMessage());
        }
    }
}
