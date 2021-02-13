package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Gift extends AbstractEntity {


    private String name;
    private String description;
    private String price;
    private String link;

   @ManyToOne
    private Occasion occasion;

    public Gift() {

    }

    public Gift(String name,String description, String price, String link, Occasion occasion) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
        this.occasion = occasion;
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

    public Occasion getOccasion() {
        return occasion;
    }

    public void setOccasion(Occasion occasion) {
        this.occasion = occasion;
    }
}
