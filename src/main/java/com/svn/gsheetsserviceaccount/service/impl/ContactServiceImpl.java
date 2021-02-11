package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.repositories.ContactRepository;
import com.svn.gsheetsserviceaccount.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public Optional<Contact> save(final Contact contact) {
        Assert.notNull(contact,"Сведения о контакте на должны быть null");

        Optional<Contact> optionalContact = Optional.empty();
        if (!existsByCode(contact.getCode())){
            optionalContact = Optional.of(contactRepository.save(contact));
        } // todo а что делать если он есть?

        return optionalContact;
    }

    @Override
    public boolean existsByCode(String code){
        Assert.hasText(code, "Значение code не должно быть пустым.");

        return contactRepository.existsContactByCode(code);
    }

    @Override
    public List<Contact> saveAll(List<Contact> contacts){
        Assert.notEmpty(contacts,"--- Список контактов должен содержать елементы. contacts: "+contacts);
        Assert.noNullElements(contacts,"--- В списке контактов не должно быть null элементов: "+ contacts);

        return contactRepository.saveAll(contacts);
    }
}
