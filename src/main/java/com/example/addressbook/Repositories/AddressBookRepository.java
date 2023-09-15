package com.example.addressbook.Repositories;

import com.example.addressbook.Entities.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {

    boolean existsByNameAndAddress1AndAddress2AndCityAndStateAndZip(String name, String address1, String address2, String city, String state, String zip);

    List<AddressBook> findAllByName(String name);

    List<AddressBook> findAllByAddress1(String address1);

    List<AddressBook> findAllByAddress2(String address2);

    List<AddressBook>  findAllByCity(String city);

    List<AddressBook>  findAllByState(String state);

    List<AddressBook>  findAllByZip(String zip);

}
