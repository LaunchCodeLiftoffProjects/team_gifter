package com.liftoff.gifter.controllers;

import com.liftoff.gifter.data.OccasionRepository;
import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Occasion;
import com.liftoff.gifter.models.OccasionTools;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("recipient")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private OccasionRepository occasionRepository;

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Recipients");
        model.addAttribute("recipients", recipientRepository.findAll());
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

//        OccasionDTO recipientOccasion = new OccasionDTO();
//        occasion.setRecipient(recipient);

//        model.addAttribute("recipientOccasion", recipientOccasion);
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

        Optional<Occasion> occasionToEdit = occasionRepository.findById(id);
        if(occasionToEdit.isPresent()) {
            occasionToEdit.get().setName(occasion.getName());
            occasionToEdit.get().setDate(occasion.getDate());
            occasionToEdit.get().setSortableDate();
            occasionToEdit.get().setRecurring(occasion.isRecurring());
            occasionRepository.save(occasionToEdit.get());
            return "redirect:detail?recipientId=" + occasionToEdit.get().getRecipient().getId();
        }

//        model.addAttribute("occasions", recipient.getOccasions());
        return "recipient/edit-occasion";
    }

    // ToDo: Build handlers to "remove" recipient
    @GetMapping(value = "remove/{id}")
    public String processRemoveRecipientForm(Model model, @PathVariable int id) {
        recipientRepository.delete(recipientRepository.findById(id).get());
        model.addAttribute("title", "Recipients");
        model.addAttribute("recipients", recipientRepository.findAll());
        return "recipient/index";
    }

}

// © 2020 Gifter
