package com.liftoff.gifter.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @ManyToOne
    private Recipient recipient;

    @OneToMany(mappedBy="occasion")
    private List<Gift> gifts = new ArrayList<>();

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
        this.sortableDate = formatter.parse(date);
    }

    public void setRecipient(Recipient recipient){
        this.recipient = recipient;
    }

    public Recipient getRecipient() {
        return recipient;
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

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public static List<Occasion> findUpcoming(List<Occasion> occasions) throws ParseException {
        Occasion.sortOccasions(occasions);
        Calendar cal1 = Calendar.getInstance();
        int currentDay = cal1.get(Calendar.DAY_OF_YEAR);
        int currentYear = cal1.get(Calendar.YEAR);
        Calendar cal2 = Calendar.getInstance();

        ArrayList<Occasion> upcoming = new ArrayList<>();

        for (int i = 0; i < occasions.size(); i++) {
            Occasion occasion = occasions.get(i);
            cal2.setTime(occasion.getSortableDate());
            int occasionDay = cal2.get(Calendar.DAY_OF_YEAR);
            int occasionYear = cal2.get(Calendar.YEAR);
            int diff = 100;

            if (occasionYear == 1000 && occasionDay > currentDay) {
                occasionYear = currentYear;
            } else if (occasionYear == 1000 && occasionDay < currentDay) {
                occasionYear = currentYear+1;
            }
            if (currentDay > 319 && occasionYear == currentYear+1) {
                diff = (364-currentDay) + occasionDay;
            } else if (occasionDay - currentDay >=0 && occasionYear == currentYear) {
                diff = (occasionDay - currentDay);
            }

            if (diff<45) {
                upcoming.add(occasion);
            }
        }
        return upcoming;
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
