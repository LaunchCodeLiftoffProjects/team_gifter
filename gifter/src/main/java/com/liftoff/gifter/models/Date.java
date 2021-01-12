package com.liftoff.gifter.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Date {
    private String day;

    private String month;

    private String year;

    private String date;

    private String longDate;

    public static final ArrayList<String> monthArr = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    );

    public static final ArrayList<String> monthNameArr = new ArrayList<String>(
            Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    );

    public static final ArrayList<String> dayArr = new ArrayList<String>(
            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31")
    );

    public Date(String day, String month) {
        this.day = day;
        this.month = month;
        this.year = null;
        this.date = day + "/" + month;
        this.longDate = monthNameArr.get(monthArr.indexOf(month)) + " " + day;
    }

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = day + "/" + month + "/" + year;
        this.longDate = monthNameArr.get(monthArr.indexOf(month)) + " " + day + ", " + year;
    }

    public Date() {};

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getLongDate() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date1 = (Date) o;
        return getDate().equals(date1.getDate()) &&
                getLongDate().equals(date1.getLongDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getLongDate());
    }
}
