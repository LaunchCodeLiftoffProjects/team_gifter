package com.liftoff.gifter.models;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

@Embeddable
public class OccasionDate implements Comparable<OccasionDate>{
    private String day;

    private String month;

    private String year;

    public static final ArrayList<String> monthArr = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    );

    public static final ArrayList<String> monthNameArr = new ArrayList<String>(
            Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    );

    public static final ArrayList<String> dayArr29 = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29")
    );
    public static final ArrayList<String> dayArr30 = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")
    );
    public static final ArrayList<String> dayArr31 = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31")
    );

    public static final ArrayList<String> yearArr = new ArrayList<String>() {{
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = currentYear; i <= currentYear+20; i++){
            add(String.valueOf(i));
        }
    }};

    public OccasionDate(String day, String month) {
        this.day = day;
        this.month = month;
        this.year = null;
    }

    public OccasionDate(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public OccasionDate() {};

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getShortDate() {
        String shortDate = day + "/" + month;
        if(year.length()>0){
            shortDate = shortDate + "/" + year;
        }
        return shortDate;
    }

    public String getLongDate() {
        String longDate = monthNameArr.get(monthArr.indexOf(month)) + " " + day;
        if(year.length()>0){
            longDate = longDate + ", " + year;
        }
        return longDate;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OccasionDate)) return false;
        OccasionDate date1 = (OccasionDate) o;
        return getShortDate().equals(date1.getShortDate()) &&
                getLongDate().equals(date1.getLongDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShortDate(), getLongDate());
    }

    @Override
    public int compareTo(OccasionDate o) {
        if (getShortDate() == null || o.getShortDate() == null)
            return 0;
        return getShortDate().compareTo(o.getShortDate());
    }
}
