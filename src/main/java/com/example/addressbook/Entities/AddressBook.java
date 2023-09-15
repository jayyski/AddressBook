package com.example.addressbook.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
@NotNull
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;

    public AddressBook(String name, String address1, String address2, String city, String state, String zip) {
        this.name = name;
        this.address1 = address1.toLowerCase();
        this.address2 = address2.toLowerCase();
        this.city = city.toLowerCase();
        this.state = state.toUpperCase();
        this.zip = zip;
    }

    public boolean isValidated(){
        return zip.length() == 5 && state.length() == 2;
    }

    @Override
    public String toString() {
        return "Name=" + name + "\n" +
                "Address1=" + address1 + "\n" +
                "Address2=" + address2 + "\n" +
                "City=" + city + "\n" +
                "State=" + state + "\n" +
                "Zip=" + zip + "\n";
    }
}
