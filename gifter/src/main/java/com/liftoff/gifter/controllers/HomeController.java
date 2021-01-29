package com.liftoff.gifter.controllers;

import com.liftoff.gifter.data.GiftRepository;
import com.liftoff.gifter.data.OccasionRepository;
import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.data.UserRepository;
import com.liftoff.gifter.models.Occasion;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/gifter")
public class HomeController {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private OccasionRepository occasionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) throws ParseException {

        model.addAttribute("title", "Welcome to Gifter");

        model.addAttribute("recipients", recipientRepository.findAll());
        List<Occasion> occasions = (List<Occasion>) occasionRepository.findAll();
        model.addAttribute("occasions", occasions);
        model.addAttribute("gifts", giftRepository.findAll());

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

        model.addAttribute("upcoming", upcoming);
        model.addAttribute("numUpcoming", upcoming.size());

        return "dashboard";
    }
}
