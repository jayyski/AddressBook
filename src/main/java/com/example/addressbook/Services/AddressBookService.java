package com.example.addressbook.Services;

import com.example.addressbook.Entities.AddressBook;
import com.example.addressbook.Repositories.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    @Autowired
    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;

    }

    public List<AddressBook> findAllByFields(String value, String field) {
        return switch (field) {
            case "name" -> addressBookRepository.findAllByName(value);
            case "address1" -> addressBookRepository.findAllByAddress1(value);
            case "address2" -> addressBookRepository.findAllByAddress2(value);
            case "city" -> addressBookRepository.findAllByCity(value);
            case "state" -> addressBookRepository.findAllByState(value);
            case "zip" -> addressBookRepository.findAllByZip(value);
            default -> null;
        };
    }
}
