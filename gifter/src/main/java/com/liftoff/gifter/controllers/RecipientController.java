package com.liftoff.gifter.controllers;


import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("recipient")
public class RecipientController {

    @Autowired
    private RecipientRepository recipientRepository;

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
        return "recipient/add";
    }

    @GetMapping("detail")
    public String displayRecipientDetails(@RequestParam Integer recipientId, Model model) {

        Optional<Recipient> result = recipientRepository.findById(recipientId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Recipient ID: " + recipientId);
        } else {
            Recipient recipient = result.get();
            model.addAttribute("title", recipient.getFirstName() + recipient.getLastName());
            model.addAttribute("recipient", recipient);
        }

        return "recipient/detail";
    }

    @GetMapping("view")
    public String viewRecipients(Model model) {
        model.addAttribute("title", "All Recipients");
        model.addAttribute("recipients", recipientRepository.findAll());

        return "recipient/view";
    }

    // ToDo: Build handlers to "edit" recipient

    // ToDo: Build handlers to "remove" recipient

}

// © 2020 Gifter
