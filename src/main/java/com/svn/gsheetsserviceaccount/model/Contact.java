package com.svn.gsheetsserviceaccount.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.util.List;

@Data
@Document
public final class Contact {

    @Id
    private final ObjectId id;
    private final String code;
    private final String name;
    private final String phone;
    private final String email;

    /**
     * Для создания объекта класса Contact использовать статический методы
     * Contact.create(String code, String name, String phone, String email)
     * или Contact.create(List<String> values)
     */
    private Contact(ObjectId id, String code, String name, String phone, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Статический метод create(List<String> values) используется для
     * созданиея объекта класса Contact из списка строк
     * полученных из внешнего источника.
     *
     * @param values список(String) присваиваемых полям класса Contact
     * @return Объект класса Contact c уникальным значением id типа org.bson.types.ObjectId
     * @throws IllegalArgumentException если values = null, values.isEmpty()=true, или название эл.почты не содержит @;
     */
    public static Contact create(List<String> values) {
        Assert.notNull(values, "Значение values не должно быть null.");
        Assert.notEmpty(values, "Значение values не должно быть пустым.");
        Assert.isTrue(values.size() > 3,
                "Нeвозможно создать контакт из передаваемого списка: " + values);

        Contact contact = Contact.create(
                values.get(0),
                values.get(1),
                values.get(2),
                values.get(3)
        );

        return contact;
    }

    /**
     * Статический метод create(String code, String name, String phone, String email) используется для
     * созданиея объекта класса Contact из Stirng переменных.
     * Значение id генерируется используя org.bson.types.ObjectId.get().
     *
     * @param code,name,phone,email String переменные присваиваемые полям класса Contact
     * @return Объект класса Contact c уникальным значением id типа org.bson.types.ObjectId
     * @throws IllegalArgumentException если значения не содержат сведения, или название эл.почты не содержит @;
     */
    public static Contact create(String code, String name, String phone, String email) {
        Assert.hasText(code, "Значение code не должно быть пустым.");
        Assert.hasText(name, "Значение name не должно быть пустым.");
        Assert.hasText(phone, "Значение phone не должно быть пустым.");
        Assert.hasText(email, "Значение email не должно быть пустым.");
        Assert.isTrue(email.contains("@"), "Проверьте наименование электронной почты: " + email);

        Contact contact = new Contact(ObjectId.get(), code, name, phone, email);
        return contact;
    }

}
