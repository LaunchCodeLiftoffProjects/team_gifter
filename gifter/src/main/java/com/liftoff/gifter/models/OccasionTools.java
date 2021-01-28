package com.liftoff.gifter.models;

import java.text.ParseException;
import java.util.*;

public abstract class OccasionTools {
//     implements Comparable<OccasionDate>

//    public static final ArrayList<String> monthArr = new ArrayList<String>(
//            Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
//    );

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

    public static List<Occasion> sortOccasions(List<Occasion> occasions) throws ParseException {

        int currentDay = (Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        int currentYear = (Calendar.getInstance().get(Calendar.YEAR));

        for(int i = 0; i < occasions.size(); i++) {
            Calendar cal = Calendar.getInstance();
            Occasion occasion = occasions.get(i);
            occasion.setSortableDate();
            cal.setTime(occasion.getSortableDate());
            int occasionDay = cal.get(Calendar.DAY_OF_YEAR);
            int occasionMonth = cal.get(Calendar.MONTH);
            int occasionDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            if (occasion.isRecurring() && occasionDay > currentDay) {
                occasion.setDate(OccasionTools.monthNameArr.get(occasionMonth) + " " + occasionDayOfMonth + ", " + currentYear);
            } else if (occasion.isRecurring() && occasionDay < currentDay) {
                occasion.setDate(OccasionTools.monthNameArr.get(occasionMonth) + " " + occasionDayOfMonth + ", " + (currentYear + 1));
            }
        }

        Collections.sort(occasions); /* occasions are now in absolute chronological order*/

        int j = 0;
        for (int i = 0; i < occasions.size(); i++) {
            Calendar cal = Calendar.getInstance();
            Occasion occasion = occasions.get(j);
            occasion.setSortableDate();
            cal.setTime(occasion.getSortableDate());
            int occasionDay = cal.get(Calendar.DAY_OF_YEAR);
            int occasionYear = cal.get(Calendar.YEAR);
            int occasionMonth = cal.get(Calendar.MONTH);
            int occasionDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

//            if (occasion.isRecurring()) {
//                occasion.setDate(OccasionTools.monthNameArr.get(occasionMonth) + " " + occasionDayOfMonth);
//            }

            if(occasionDay < currentDay || occasionYear > currentYear) {
                occasions.add(occasion);
                occasions.remove(occasion);
            } else {
                j++;
            }
        }

        return occasions;
    }
}
