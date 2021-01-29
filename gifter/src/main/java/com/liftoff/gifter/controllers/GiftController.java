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
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    private GiftRepository giftRepository;
//    private Gift gift;
    @Autowired
    private RecipientRepository recipientRepository;



    @GetMapping(value = {"add", "add/{recipientId}"})
    public String displayAddGiftForm(Model model, @PathVariable(required=false) Integer recipientId) {
        model.addAttribute("title", "Add Gift");
        model.addAttribute( new Gift());
        model.addAttribute("recipients", recipientRepository.findAll());

        return "gift/add";
    }


    @PostMapping(value = {"add", "add/{recipientId}"})
    public String processAddGiftForm(@ModelAttribute @Valid Gift newGift, Integer id, Errors errors, Model model){

        if (errors.hasErrors()){
            return "gift/add";
        }

        giftRepository.save(newGift);
        return "gift/add-occasion";

    }

    
    @GetMapping("edit/{recipientId}")
    public String displayEditForm(Model model, @PathVariable int recipientId) {
        Gift giftToEdit = giftRepository.findById(recipientId).get();
        model.addAttribute("title", "Edit Gift");
        model.addAttribute("gift", giftToEdit);
        return "gift/edit";
    }


    @PostMapping(value="edit")
    public String processEditForm(@ModelAttribute @Valid Gift gift, int id,
                                  Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Gift");
            return "gift/edit";
        }
       Gift giftToEdit = giftRepository.findById(id).get();
        giftToEdit.setGiftName(gift.getGiftName());
        giftToEdit.setDescription(gift.getDescription());
        giftToEdit.setPrice(gift.getPrice());
        giftToEdit.setLink(gift.getLink());
        giftRepository.save(giftToEdit);
        return "redirect:gift?recipientId=" + giftToEdit.getId();
    }





    @GetMapping( "view/{recipientId}")
        public String displayViewGift(Model model, @PathVariable int recipientId){
            Optional<Gift> optGift = giftRepository.findById(recipientId);
            if(optGift.isPresent()){
                Gift gift = (Gift) optGift.get();
                model.addAttribute("gift", "gift");
                return "gift/view";
            } else{
                return "redirect:../";
            }
        }


    }