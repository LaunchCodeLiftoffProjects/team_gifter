package com.liftoff.gifter.models;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipient extends AbstractEntity {

    @NotNull
    @Size(min=2, max=20, message="First name is required. Must have 2 to 20 characters.")
    private String firstName;

    @NotNull
    @Size(min=2, max=20, message="Last name is required. Must have 2 to 20 characters.")
    private String lastName;

    @Email(message="Please enter a valid email.")
    private String email;

    private String phoneNumber;

    private String address;

    private String relationship;

    @ManyToMany
    private final List<Occasion> occasions = new ArrayList<>();

    private Date dateCreated;

    private Date dateUpdated;

    public Recipient() {
        dateCreated = new java.util.Date(System.currentTimeMillis());
        dateUpdated = new java.util.Date(System.currentTimeMillis());
    }

    public Recipient(@NotNull @Size(min = 3, max = 20, message = "First name must have 3 to 20 characters.") String firstName,
                     @NotNull @Size(min = 3, max = 20, message = "Last name must have 3 to 20 characters.") String lastName,
                     @Email(message = "Email address must be properly formed.")
                     String email,
                     String relationship,
                     String address,
                     String phoneNumber,
                     Date dateCreated,
                     Date dateUpdated) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.relationship = relationship;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFormattedCreationDate(){
        return new SimpleDateFormat("MMM d yyyy hh:mm a").format(getDateCreated());
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public String getFormattedUpdatedDate(){
        return new SimpleDateFormat("MMM d yyyy hh:mm a").format(getDateUpdated());
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Occasion> getOccasions() {
        return occasions;
    }

    public void addOccasion(Occasion occasion) { this.occasions.add(occasion); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(firstName, recipient.firstName)
                && Objects.equals(lastName, recipient.lastName)
                && Objects.equals(email, recipient.email)
                && Objects.equals(relationship, recipient.relationship)
                && Objects.equals(address, recipient.address)
                && Objects.equals(phoneNumber, recipient.phoneNumber)
                && Objects.equals(dateCreated, recipient.dateCreated)
                && Objects.equals(dateUpdated, recipient.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName,
                email, relationship, address, phoneNumber, dateCreated, dateUpdated);
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", relationship='" + relationship + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
// © 2020 Gifter