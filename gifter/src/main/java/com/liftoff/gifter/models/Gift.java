package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Gift extends AbstractEntity {

    private String description;
   @ManyToMany
    private List<Recipient> recipients = new ArrayList<Recipient>();

    public Gift() {

    }

    public Gift(String description, List<Recipient> recipients) {
        this.description = description;
        this.recipients = recipients;
    }

    public String getDescription() {return description; }

    public void setLocation(String description) {this.description = description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}
