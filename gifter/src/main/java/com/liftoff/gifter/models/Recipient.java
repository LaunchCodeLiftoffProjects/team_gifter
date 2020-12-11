package com.liftoff.gifter.models;


import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Recipient extends AbstractEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @Basic
    private Date dateOfBirth;

//    @CollectionTable
//    private Array categories;

    private String relationship;

    @Basic
    private Date anniversary;


    private String address;


    private String phoneNumber;



    public Recipient() {}

    public Recipient(@NotNull String firstName, @NotNull String lastName,
                     @Email String email, Date dateOfBirth,
                     Array categories, String relationship, Date anniversary,
                     String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
//        this.categories = categories;
        this.relationship = relationship;
        this.anniversary = anniversary;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
//                ", categories=" + categories +
                ", relationship='" + relationship + '\'' +
                ", anniversary=" + anniversary +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(firstName, recipient.firstName)
                && Objects.equals(lastName, recipient.lastName)
                && Objects.equals(email, recipient.email)
                && Objects.equals(dateOfBirth, recipient.dateOfBirth)
//                && Objects.equals(categories, recipient.categories)
                && Objects.equals(relationship, recipient.relationship)
                && Objects.equals(anniversary, recipient.anniversary)
                && Objects.equals(address, recipient.address)
                && Objects.equals(phoneNumber, recipient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email,
                dateOfBirth, /*categories,*/ relationship, anniversary,
                address, phoneNumber);
    }
}
