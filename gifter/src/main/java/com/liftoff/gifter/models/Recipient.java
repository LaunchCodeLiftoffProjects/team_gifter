package com.liftoff.gifter.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class Recipient {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1, max=50, message="First name must have from 1 to 50 characters.")
    private String firstName;

    @NotNull
    @Size(min=1, max=50, message="Last name must have from 1 to 50 characters.")
    private String lastName;

    private Date dateOfBirth;

    @ManyToOne
    private Category category;





    @Email(message="Email address must be properly formed.")
    private String email;

    //address

    //phone number

    //categories

    //relationship

    //anniversary

}
