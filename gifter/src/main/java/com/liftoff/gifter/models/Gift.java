package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gift extends AbstractEntity {

    private String name;
    private String description;
    private String price;
    private String link;
   @ManyToMany
    private List<Recipient> recipients = new ArrayList<Recipient>();

    public Gift() {

    }

    public Gift(String name, String description, String price, String link, List<Recipient> recipients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.recipients = recipients;
    }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public String getDescription() {return description; }

    public void setDescription(String description) {this.description = description; }

    public String getPrice() { return price; }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() { return link; }

    public void setLink(String link) {
        this.link= link;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}
