package com.liftoff.gifter.controllers;

import com.liftoff.gifter.AuthenticationFilter;
import com.liftoff.gifter.data.OccasionRepository;
import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.data.UserRepository;
import com.liftoff.gifter.models.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("recipient")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private OccasionRepository occasionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping(value = "")
    public String index(Model model) throws ParseException {
        model.addAttribute("title", "Recipients");
        List<Recipient> recipients = (List<Recipient>) recipientRepository.findAll();
        Collections.sort(recipients);
        model.addAttribute("recipients", recipients);

        for (Recipient recipient : recipients) {
            Occasion.findUpcoming(recipient.getOccasions());
        }

        return "recipient/index";
    }

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
    public String displayRecipientDetails(@RequestParam Integer recipientId, Model model) throws ParseException {

        Optional<Recipient> result = recipientRepository.findById(recipientId);


        //TODO: fix this to show proper error message
        if (result.isEmpty()) {
            model.addAttribute("title", "Recipient Does Not Exist");
        } else {
            Recipient recipient = result.get();
            List<Occasion> occasions = recipient.getOccasions();

            Occasion.sortOccasions(occasions);

            //deletes non-recurring dates that have passed.
            //NOTE: they are deleted from the recipient but not the repository. This leaves possibility for them to be restored or archived for future reference.
            java.util.Date currentDate = new java.util.Date();
            ArrayList<Occasion> toBeRemoved = new ArrayList<>();
            for (Occasion occasion : occasions) {
                if (!occasion.isRecurring() && occasion.getSortableDate().compareTo(currentDate) < 0) {
//                    occasionRepository.delete(occasion);
                    toBeRemoved.add(occasion);
                }
            }

            for (Occasion occasion : toBeRemoved) {
                occasions.remove(occasion);
            }
            recipient.setOccasions(occasions);

            model.addAttribute("title", recipient.getFirstName() + ' ' + recipient.getLastName());
            model.addAttribute("recipient", recipient);
            model.addAttribute("occasions", occasions);
        }
        return "recipient/detail";
    }

    @GetMapping("edit/{recipientId}")
    public String displayEditForm(Model model, @PathVariable int recipientId) {
        Recipient recipientToEdit = recipientRepository.findById(recipientId).get();
        model.addAttribute("title", "Edit Recipient");
        model.addAttribute("recipient", recipientToEdit);
        return "recipient/edit";
    }

    @PostMapping(value="edit")
    public String processEditForm(@ModelAttribute @Valid Recipient recipient, int id,
                                  Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Recipient");
            return "recipient/edit";
        }
        Date updated = new java.util.Date(System.currentTimeMillis());
        Recipient recipientToEdit = recipientRepository.findById(id).get();
        recipientToEdit.setFirstName(recipient.getFirstName());
        recipientToEdit.setLastName(recipient.getLastName());
        recipientToEdit.setEmail(recipient.getEmail());
        recipientToEdit.setRelationship(recipient.getRelationship());
        recipientToEdit.setAddress(recipient.getAddress());
        recipientToEdit.setPhoneNumber(recipient.getPhoneNumber());
        recipientToEdit.setDateUpdated(updated);
        recipientRepository.save(recipientToEdit);
        model.addAttribute("occasions", recipient.getOccasions());
        return "redirect:detail?recipientId=" + recipientToEdit.getId();
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

        Occasion occasion = new Occasion();
        occasion.setRecurring(true);
        occasion.setRecipient(recipient);
        model.addAttribute(occasion);

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

        Collections.sort(occasions);

        model.addAttribute("occasions", occasions);

        model.addAttribute("monthNames", OccasionTools.monthNameArr);
        model.addAttribute("days29", OccasionTools.dayArr29);
        model.addAttribute("days30", OccasionTools.dayArr30);
        model.addAttribute("days31", OccasionTools.dayArr31);
        model.addAttribute("years", OccasionTools.yearArr);

        return "recipient/add-occasion";
    }

    @PostMapping("add-occasion")
    public String processAddOccasionForm(@ModelAttribute @Valid Occasion newOccasion,
                                    Errors errors,
                                    Model model) throws ParseException {

        if (!errors.hasErrors()) {
            Recipient recipient = newOccasion.getRecipient();

            if (!recipient.occasionNameAlreadyExists(newOccasion.getName()) && newOccasion.getName().length() > 0){
                recipient.addOccasion(newOccasion);
//                occasion.setSortableDate();
                occasionRepository.save(newOccasion);
                recipientRepository.save(recipient);
            }
            return "redirect:detail?recipientId=" + recipient.getId();
        }

        Recipient recipient = newOccasion.getRecipient();
        return "redirect:add-occasion?recipientId=" + recipient.getId();
    }

    @GetMapping("edit-occasion/{occasionId}")
    public String displayEditOccasionForm(Model model, @PathVariable int occasionId) {
        Occasion occasionToEdit = occasionRepository.findById(occasionId).get();
        model.addAttribute("title", "Edit " + occasionToEdit.getName());
        model.addAttribute("occasion", occasionToEdit);

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

        Collections.sort(occasions);

        model.addAttribute("occasions", occasions);

        model.addAttribute("monthNames", OccasionTools.monthNameArr);
        model.addAttribute("days29", OccasionTools.dayArr29);
        model.addAttribute("days30", OccasionTools.dayArr30);
        model.addAttribute("days31", OccasionTools.dayArr31);
        model.addAttribute("years", OccasionTools.yearArr);

        return "recipient/edit-occasion";
    }

    @PostMapping(value="edit-occasion")
    public String processEditOccasionForm(@ModelAttribute @Valid Occasion occasion, Integer id,
                                  Errors errors, Model model) throws ParseException {
        if (errors.hasErrors()){
            model.addAttribute("title", "Edit Occasion");
            return "recipient/edit-occasion";
        }

        Occasion occasionToEdit = occasionRepository.findById(id).get();
        occasionToEdit.setName(occasion.getName());
        occasionToEdit.setDate(occasion.getDate());
        occasionToEdit.setSortableDate();
        occasionToEdit.setRecurring(occasion.isRecurring());
        occasionRepository.save(occasionToEdit);
//        model.addAttribute("occasions", recipient.getOccasions());
        return "redirect:detail?recipientId=" + occasionToEdit.getRecipient().getId();
    }

    // ToDo: Build handlers to "remove" recipient

}

// © 2020 Gifter
