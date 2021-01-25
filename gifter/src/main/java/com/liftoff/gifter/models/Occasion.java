package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Occasion extends AbstractEntity implements Comparable<Occasion>{
    @Size(min = 1, max = 25)
    @NotBlank(message = "Please choose an occasion")
    private String name;

    @NotBlank(message = "please select a valid date")
    private String date;

    private java.util.Date sortableDate;

    public static SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");

    private boolean recurring;

    private static ArrayList<String> standardOccasions = new ArrayList<>(
            Arrays.asList("Christmas", "Mother's Day", "Father's Day", "Birthday", "Anniversary", "Graduation", "Valentine's Day", "Hanukkah", "Bar/Bat Mitzvah")
    );

    @ManyToMany(mappedBy = "occasions")
    private final List<Recipient> recipients = new ArrayList<>();

    public Occasion(String name, String date, boolean recurring) {

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

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getSortableDate() {
        return sortableDate;
    }

    public void setSortableDate() throws ParseException{
        this.sortableDate = formatter.parse(date);;
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
        Calendar cal1 = Calendar.getInstance();
        try {
            this.setSortableDate();
            o.setSortableDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal1.setTime(this.getSortableDate());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(o.getSortableDate());

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);


        if(year1 < year2) {
            return -1;
        } else if(year1 == year2 && month1 < month2) {
            return -1;
        } else if(month1 == month2) {
            return cal1.get(Calendar.DAY_OF_MONTH) - cal2.get(Calendar.DAY_OF_MONTH);
        } else {
            return 1;
        }

    }

}
