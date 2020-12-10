package com.liftoff.gifter.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 25)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Recipient> recipients = new ArrayList<>();

}
