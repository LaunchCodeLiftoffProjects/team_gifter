package com.liftoff.gifter.models;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
public class Recipient extends AbstractEntity {

    @NotNull
    @Size(min=3, max=20, message="First name must have 3 to 20 characters.")
    private String firstName;

    @NotNull
    @Size(min=3, max=20, message="Last name must have 3 to 20 characters.")
    private String lastName;

    @Email(message="Email address must be properly formed.")
    private String email;

    @Basic
    private Date dateOfBirth;

    private String relationship;

    @Basic
    private Date anniversary;

    private String address;

    private String phoneNumber;

    private Date creationDate;

    private Date updated;


    public Recipient() {
        creationDate = new java.sql.Date(System.currentTimeMillis());
        updated = new java.sql.Date(System.currentTimeMillis());
    }

    public Recipient(@NotNull @Size(min = 3, max = 20, message = "First name must have 3 to 20 characters.") String firstName,
                     @NotNull @Size(min = 3, max = 20, message = "Last name must have 3 to 20 characters.") String lastName,
                     @Email(message = "Email address must be properly formed.")
                             String email,
                     Date dateOfBirth,
                     String relationship,
                     Date anniversary,
                     String address,
                     String phoneNumber,
                     Date creationDate,
                     Date updated) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.relationship = relationship;
        this.anniversary = anniversary;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Date getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(Date anniversary) {
        this.anniversary = anniversary;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getFormattedCreationDate(){
        return new SimpleDateFormat("MMM d yyyy hh:mm a").format(getCreationDate());
    }

    public String getFormattedUpdatedDate(){
        return new SimpleDateFormat("MMM d yyyy hh:mm a").format(getUpdated());
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
                && Objects.equals(relationship, recipient.relationship)
                && Objects.equals(anniversary, recipient.anniversary)
                && Objects.equals(address, recipient.address)
                && Objects.equals(phoneNumber, recipient.phoneNumber)
                && Objects.equals(creationDate, recipient.creationDate)
                && Objects.equals(updated, recipient.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName,
                email, dateOfBirth, relationship, anniversary,
                address, phoneNumber, creationDate, updated);
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", relationship='" + relationship + '\'' +
                ", anniversary=" + anniversary +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateCreated=" + creationDate +
                ", updated=" + updated +
                '}';
    }
}
// © 2020 Gifter