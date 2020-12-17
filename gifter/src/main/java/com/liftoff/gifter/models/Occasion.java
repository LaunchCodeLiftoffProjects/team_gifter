package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Occasion extends AbstractEntity{
    @Size(min = 1, max = 25)
    @NotBlank
    private String name;

    @NotBlank
    private Date date;

    @ManyToMany(mappedBy = "occasions")
    private final List<Recipient> recipients = new ArrayList<>();

    public Occasion(String name, Date date) {

        this.name = name;
        this.date = date;
    }

    public Occasion() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }
}
