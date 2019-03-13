package com.muditasoft.petclinic.controller;

import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetclinicController {

    @Autowired
    private PetclinicService petclinicService;

    @GetMapping("/owners")
    public String getOwners(Model model) {
        model.addAttribute("owners", petclinicService.findOwners());
        return "index";
    }
}
