//package com.liftoff.gifter;
//
//import com.liftoff.gifter.models.Occasion;
//import com.liftoff.gifter.models.OccasionDate;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//class OccasionTests {
//
//    Occasion testOccasion1;
//    Occasion testOccasion2;
//    Occasion testOccasion3;
//
//    ArrayList<Occasion> occasionArray = new ArrayList<>();
//    ArrayList<Occasion> sortedArray = new ArrayList<>();
//
//    OccasionDate testDate1;
//    OccasionDate testDate2;
//    OccasionDate testDate3;
//
////    @Before("")
////    public void createOccasionDate() {
////        testDate1 = new OccasionDate("04", "08", "2021");
////        testDate2 = new OccasionDate("30", "08", "2021");
////        testDate3 = new OccasionDate("28", "02", "2020");
////    }
////
////    @Before("")
////    public void createOccasionArray() {
////        testOccasion1 = new Occasion("Birthday", testDate1, false);
////        testOccasion2 = new Occasion("Anniversary", testDate2, false);
////        testOccasion3 = new Occasion("Holiday", testDate3, false);
////
////        occasionArray.add(testOccasion1);
////        occasionArray.add(testOccasion2);
////        occasionArray.add(testOccasion3);
////
////        sortedArray.add(testOccasion3);
////        sortedArray.add(testOccasion1);
////        sortedArray.add(testOccasion2);
////    }
//
//    @Test
//    public void emptyTest() {
//        assertEquals(10,10,.001);
//    }
//
//    @Test
//    public void testSortByDate() {
//        testDate1 = new OccasionDate("04", "08", "2022");
//        testDate2 = new OccasionDate("30", "08", "2022");
//        testDate3 = new OccasionDate("28", "02", "2021");
//
//        testOccasion1 = new Occasion("Birthday", testDate1, false);
//        testOccasion2 = new Occasion("Anniversary", testDate2, false);
//        testOccasion3 = new Occasion("Holiday", testDate3, false);
//
//        occasionArray.add(testOccasion1);
//        occasionArray.add(testOccasion2);
//        occasionArray.add(testOccasion3);
//
//        sortedArray.add(testOccasion3);
//        sortedArray.add(testOccasion1);
//        sortedArray.add(testOccasion2);
//
//        System.out.println("Original: " + occasionArray);
////        System.out.println("Sorted: " + Occasion.sortByDate(occasionArray));
//        System.out.println("Correct: " + sortedArray);
//        assertEquals(sortedArray, Occasion.sortByDate(occasionArray));
//    }
//
//}
