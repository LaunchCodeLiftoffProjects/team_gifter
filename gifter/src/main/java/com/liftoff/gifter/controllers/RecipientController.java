package com.liftoff.gifter.controllers;


import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    // ToDo: Build handlers to "edit" recipient

    // ToDo: Build handlers to "remove" recipient


}

// © 2020 Gifter
