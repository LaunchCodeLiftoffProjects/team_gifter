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

        List<Occasion> upcoming = Occasion.findUpcoming(occasions);

        model.addAttribute("upcoming", upcoming);
        model.addAttribute("numUpcoming", upcoming.size());

        return "dashboard";
    }
}
