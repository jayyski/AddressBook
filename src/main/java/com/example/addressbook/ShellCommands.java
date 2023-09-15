package com.example.addressbook;

import com.example.addressbook.Entities.AddressBook;
import com.example.addressbook.Repositories.AddressBookRepository;
import com.example.addressbook.Services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ShellCommands {

    private final AddressBookRepository addressBookRepository;
    private final AddressBookService addressBookService;

    @Autowired
    public ShellCommands(AddressBookRepository addressBookRepository, AddressBookService addressBookService) {
        this.addressBookRepository = addressBookRepository;
        this.addressBookService = addressBookService;
    }

    @ShellMethod("Add address to address book")
    public String add(String firstName, String lastName, String address1, String address2, String city, String state, String zip) {
        String name = firstName.toLowerCase() + " " + lastName.toLowerCase();
        AddressBook addressBook = new AddressBook(name, address1, address2, city, state, zip);
        if (!addressBook.isValidated()) {
            return "Address not valid";
        }
        if (addressBookRepository.existsByNameAndAddress1AndAddress2AndCityAndStateAndZip(name, address1, address2, city, state, zip)) {
            return "Address already exists";
        }
        addressBookRepository.save(addressBook);
        return "Address added\n" + addressBook;
    }

    @ShellMethod("List all addresses in address book")
    public String list() {
        StringBuilder builder = new StringBuilder();
        List<AddressBook> address = addressBookRepository.findAll();
        for (AddressBook addressBook : address) {
            builder.append(addressBook.toString());
            builder.append("--------------------------------------------------\n");
        }
        return builder.toString();
    }

    @ShellMethod("Search address by a field")
    public String search(String field, String value) {
        String[] fields = {"name", "address1", "address2", "city", "state", "zip"};
        field = field.toLowerCase();

        if (!Arrays.asList(fields).contains(field)) {
            return "Invalid field";
        }

        List<AddressBook> addressBook = Optional.ofNullable(addressBookService.findAllByFields(value, field)).get();

        if (addressBook.isEmpty()) {
            return "No address found";
        }

        StringBuilder builder = new StringBuilder();
        for (AddressBook address : addressBook) {
            builder.append(address.toString());
            builder.append("--------------------------------------------------\n");
        }
        return builder.toString();
    }
}


