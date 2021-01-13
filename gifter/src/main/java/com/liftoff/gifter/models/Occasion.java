package com.liftoff.gifter.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Occasion extends AbstractEntity implements Comparable<Occasion>{
    @Size(min = 1, max = 25)
    @NotBlank(message = "Please choose an occasion")
    private String name;

    @Embedded
//    @NotBlank(message = "please select a date")
    private OccasionDate date;

    private boolean recurring;

    private static ArrayList<String> standardOccasions = new ArrayList<>(
            Arrays.asList("Christmas", "Mother's Day", "Father's Day", "Birthday", "Anniversary", "Graduation", "Valentine's Day", "Hanukkah", "Bar/Bat Mitzvah")
    );

    @ManyToMany(mappedBy = "occasions")
    private final List<Recipient> recipients = new ArrayList<>();

    public Occasion(String name, OccasionDate date, boolean recurring) {

        this.name = name;
        this.date = date;
        this.recurring = recurring;
    }

    public Occasion() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OccasionDate getDate() { return date; }

    public String getShortDate() {
        return date.getShortDate();
    }

    public String getLongDate() {
        return date.getLongDate();
    }

//    TODO: create method to format the date for optimal readability on recipient detail page
//    public String getFormattedDate(){
//        String formattedDate = this.getDate();
//
//        return formattedDate;
//    }

    public void setDate(OccasionDate date) {
        this.date = date;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public static ArrayList<String> getStandardOccasions() {
        return standardOccasions;
    }

    public static void setStandardOccasions(ArrayList<String> standardOccasions) {
        Occasion.standardOccasions = standardOccasions;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    @Override
    public int compareTo(Occasion o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }

}
