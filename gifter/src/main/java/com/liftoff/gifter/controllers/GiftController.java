package com.liftoff.gifter.controllers;

import com.liftoff.gifter.data.GiftRepository;
import com.liftoff.gifter.models.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    private GiftRepository giftRepository;
    //private Gift gift;


    @GetMapping("add")
    public String displayAddGiftForm(Model model) {
        model.addAttribute( new Gift());
        return "gifts/add";
    }

   // public String processAddGiftForm(Gift Object newGift;
      //   Errors errors, Model model){

       // if (errors.hasErrors()){
          //  return "add";
       // }

        //giftRepository.save(newGift);

       // return "redirect:...../";


    //}

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
