package com.liftoff.gifter.controllers;

import com.liftoff.gifter.data.GiftRepository;
import com.liftoff.gifter.data.RecipientRepository;
import com.liftoff.gifter.models.Gift;
import com.liftoff.gifter.models.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    private GiftRepository giftRepository;
//    private Gift gift;
    @Autowired
    private RecipientRepository recipientRepository;



    //private Gift gift;

    @GetMapping("add")
    public String displayAddGiftForm(Model model) {
        model.addAttribute("title", "Add Gift");
        model.addAttribute( new Gift());
        model.addAttribute("recipients", recipientRepository.findAll());

        return "gift/add";
    }


    @PostMapping("add")
    public String processAddGiftForm(@ModelAttribute @Valid Gift newGift, Integer id, Errors errors, Model model){

        if (errors.hasErrors()){
            return "gift/add";
        }
        Recipient recipientToEdit = recipientRepository.findById(id).get();
//        recipientToEdit
        giftRepository.save(newGift);
        return "gift/add";


    }


//    @GetMapping( "view/{recipientId}")
//        public String displayViewGift(Model model, @PathVariable int recipientId){
//            Optional<Gift> optGift = giftRepository.findById(recipientId);
//            if(optGift.isPresent()){
//                Gift gift = (Gift) optGift.get();
//                model.addAttribute("gift", "gift");
//                return "gift/view";
//            } else{
//                return "redirect:../";
//            }
//        }


    }
