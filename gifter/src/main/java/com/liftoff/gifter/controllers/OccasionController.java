//package com.liftoff.gifter.controllers;
//
//import com.liftoff.gifter.data.OccasionRepository;
//import com.liftoff.gifter.data.RecipientRepository;
//import com.liftoff.gifter.models.Occasion;
//import com.liftoff.gifter.models.OccasionTools;
//import com.liftoff.gifter.models.Recipient;
//import com.liftoff.gifter.models.dto.OccasionDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Optional;
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
//
//        OccasionDTO recipientOccasion = new OccasionDTO();
//        recipientOccasion.setRecipient(recipient);
//
//        model.addAttribute("recipientOccasion", recipientOccasion);
//        Occasion occasion = new Occasion();
//        occasion.setRecurring(true);
//        model.addAttribute(occasion);
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
//
//        model.addAttribute("monthNames", OccasionTools.monthNameArr);
//        model.addAttribute("days29", OccasionTools.dayArr29);
//        model.addAttribute("days30", OccasionTools.dayArr30);
//        model.addAttribute("days31", OccasionTools.dayArr31);
//        model.addAttribute("years", OccasionTools.yearArr);
//
//        return "recipient/add-occasion";
//    }
//
//    @PostMapping("add")
//    public String processAddOccasionForm(@ModelAttribute @Valid OccasionDTO recipientOccasion, @ModelAttribute @Valid Recipient saveRecipient,
//                                         Errors errors,
//                                         Model model) throws ParseException {
//
//        if (!errors.hasErrors()) {
//            Recipient recipient = recipientOccasion.getRecipient();
//            Occasion occasion = recipientOccasion.getOccasion();
//
//            if (!recipient.occasionNameAlreadyExists(occasion.getName()) && occasion.getName().length() > 0){
//                recipient.addOccasion(occasion);
//                occasionRepository.save(occasion);
//                recipientRepository.save(recipient);
//            }
//            return "recipient/detail";
//        }
//
//        Recipient recipient = recipientOccasion.getRecipient();
//        return "redirect:add?recipientId=" + recipient.getId();
//    }
//
//}
