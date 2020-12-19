package com.liftoff.gifter.controllers;

import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@RequestMapping("recipient")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Recipients");
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
        return "recipient/index";
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
        Date updated = new java.sql.Date(System.currentTimeMillis());
        Recipient recipientToEdit = recipientRepository.findById(id).get();
        recipientToEdit.setFirstName(recipient.getFirstName());
        recipientToEdit.setLastName(recipient.getLastName());
        recipientToEdit.setEmail(recipient.getEmail());
        recipientToEdit.setDateOfBirth(recipient.getDateOfBirth());
        recipientToEdit.setRelationship(recipient.getRelationship());
        recipientToEdit.setAnniversary(recipient.getAnniversary());
        recipientToEdit.setAddress(recipient.getAddress());
        recipientToEdit.setPhoneNumber(recipient.getPhoneNumber());
        recipientToEdit.setUpdated(updated);
        recipientRepository.save(recipientToEdit);
        return "redirect:";
    }

}
// © 2020 Gifter
