package com.liftoff.gifter.controllers;


import com.liftoff.gifter.data.OccasionRepository;
import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Occasion;
import com.liftoff.gifter.models.Recipient;
import com.liftoff.gifter.models.dto.OccasionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("recipient")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private OccasionRepository occasionRepository;

    // ToDo: finish this index handler for '/recipient' path
//    @RequestMapping(value = "")
//    public String index(Model model) {
//        model.addAttribute("title", "Recipients");
//        return "recipients/index";
//    }


    @GetMapping("add")
    public String displayAddRecipientForm(Model model) {
        model.addAttribute("title", "Add Recipient");
        model.addAttribute(new Recipient());  // equivalent to ("recipient", new Recipient);

        return "recipient/add";
    }

    @PostMapping("add")
    public String processAddRecipientForm(@ModelAttribute @Valid Recipient newRecipient,
                                          Errors errors, Model model) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Recipient");
            return "recipient/add";
        }
        recipientRepository.save(newRecipient);
        return "recipient/detail";
    }

    @GetMapping("detail")
    public String displayRecipientDetails(@RequestParam Integer recipientId, Model model) {

        Optional<Recipient> result = recipientRepository.findById(recipientId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Recipient Does Not Exist");
        } else {
            Recipient recipient = result.get();
            model.addAttribute("title", recipient.getFirstName() + ' ' + recipient.getLastName());
            model.addAttribute("recipient", recipient);
            model.addAttribute("occasions", recipient.getOccasions());
        }

        return "recipient/detail";
    }

    @GetMapping("view")
    public String viewRecipients(Model model) {
        model.addAttribute("title", "All Recipients");
        model.addAttribute("recipients", recipientRepository.findAll());

        return "recipient/view";
    }

    @GetMapping("add-occasion")
    public String displayAddOccasionForm(@RequestParam Integer recipientId, Model model){
        Optional<Recipient> result = recipientRepository.findById(recipientId);
        Recipient recipient = result.get();
        model.addAttribute("title", "Add Occasions For " + recipient.getFirstName() + ' ' + recipient.getLastName());
        OccasionDTO recipientOccasion = new OccasionDTO();
        recipientOccasion.setRecipient(recipient);
        model.addAttribute("recipientOccasion", recipientOccasion);
        model.addAttribute(new Occasion());

        ArrayList<String> standardOccasions = Occasion.getStandardOccasions();
        ArrayList<Occasion> customOccasions = (ArrayList<Occasion>) occasionRepository.findAll();
        ArrayList<String> occasions = new ArrayList<>();

        for(int j = 0; j<standardOccasions.size(); j++){
            occasions.add(standardOccasions.get(j));
        }

        if(customOccasions.size()>0){
            for(int i = 0; i < customOccasions.size(); i++){
                String occasionName = customOccasions.get(i).getName();
                if(!standardOccasions.contains(occasionName)){
                    occasions.add(occasionName);
                }
            }
        }

        model.addAttribute("occasions", occasions);
        return "recipient/add-occasion";
    }

    @PostMapping("add-occasion")
    public String processAddOccasionForm(@ModelAttribute @Valid OccasionDTO recipientOccasion,
                                    Errors errors,
                                    Model model){

        if (!errors.hasErrors()) {
            Recipient recipient = recipientOccasion.getRecipient();
            Occasion occasion = recipientOccasion.getOccasion();

            if (!recipient.getOccasions().contains(occasion)){
                recipient.addOccasion(occasion);
                occasionRepository.save(occasion);
                recipientRepository.save(recipient);
            }
            return "redirect:detail?recipientId=" + recipient.getId();
        }

        return "redirect:add-occasion";
    }

    // ToDo: Build handlers to "edit" recipient

    // ToDo: Build handlers to "remove" recipient

}

// © 2020 Gifter
