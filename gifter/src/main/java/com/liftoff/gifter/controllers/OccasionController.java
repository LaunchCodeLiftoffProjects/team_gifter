//package com.liftoff.gifter.controllers;
//
//
//import com.liftoff.gifter.data.OccasionRepository;
//import com.liftoff.gifter.data.RecipientRepository;
//import com.liftoff.gifter.models.Occasion;
//import com.liftoff.gifter.models.Recipient;
//import com.liftoff.gifter.models.dto.OccasionDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//
//@Controller
//@RequestMapping("occasion")
//public class OccasionController {
//
//    @Autowired
//    private RecipientRepository recipientRepository;
//
//    @Autowired
//    private OccasionRepository occasionRepository;
//
//    @GetMapping("add")
//    public String displayAddOccasionForm(@RequestParam Integer recipientId, Model model){
//        Optional<Recipient> result = recipientRepository.findById(recipientId);
//        Recipient recipient = result.get();
//        model.addAttribute("title", "Add Occasions For " + recipient.getFirstName() + ' ' + recipient.getLastName());
//        OccasionDTO recipientOccasion = new OccasionDTO();
//        recipientOccasion.setRecipient(recipient);
//        model.addAttribute("recipientOccasion", recipientOccasion);
//        model.addAttribute(new Occasion());
//
//        ArrayList<String> standardOccasions = Occasion.getStandardOccasions();
//        ArrayList<Occasion> customOccasions = (ArrayList<Occasion>) occasionRepository.findAll();
//        ArrayList<String> occasions = new ArrayList<>();
//
//        for(int j = 0; j<standardOccasions.size(); j++){
//            occasions.add(standardOccasions.get(j));
//        }
//
//        if(customOccasions.size()>0){
//            for(int i = 0; i < customOccasions.size(); i++){
//                String occasionName = customOccasions.get(i).getName();
//                if(!standardOccasions.contains(occasionName)){
//                    occasions.add(occasionName);
//                }
//            }
//        }
//
//        Collections.sort(occasions);
//
//        model.addAttribute("occasions", occasions);
//        return "recipient/add-occasion";
//    }
//
//    @PostMapping("add")
//    public String processAddOccasionForm(@ModelAttribute @Valid OccasionDTO recipientOccasion,
//                                         Errors errors,
//                                         Model model){
//
//        if (!errors.hasErrors()) {
//            Recipient recipient = recipientOccasion.getRecipient();
//            Occasion occasion = recipientOccasion.getOccasion();
//            ArrayList<String> existingOccasions = new ArrayList<>();
//            boolean alreadyExists = false;
//            //must check if recipient already has an occasion by that name
//            for(int i = 0; i < recipient.getOccasions().size(); i++) {
//                String currentOccasion = recipient.getOccasions().get(i).getName();
//                existingOccasions.add(currentOccasion);
//            }
//            if(existingOccasions.contains(occasion.getName())){
//                alreadyExists = true;
//            }
//
//            if (!alreadyExists && occasion.getName().length() > 0){
//                recipient.addOccasion(occasion);
//                occasionRepository.save(occasion);
//                recipientRepository.save(recipient);
//            }
//            return "recipient/detail";
//        }
//
//        return "redirect:add";
//    }
//
//    // ToDo: Build handlers to "remove" recipient
//
//}
//
//// © 2020 Gifter
