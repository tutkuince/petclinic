package com.muditasoft.petclinic.controller;

import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private PetclinicService petclinicService;

    @Autowired
    public OwnerController(PetclinicService petclinicService) {
        this.petclinicService = petclinicService;
    }

    @GetMapping("/new")
    public String createNewOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "createOwnerForm";
    }

    @PostMapping("/saveOwner")
    public String createNewOwnerForm(Owner owner) {
        petclinicService.createOwner(owner);
        return "redirect:/owners";
    }

    @GetMapping("/updateForm")
    public String updateOwnerForm(Model model, @RequestParam("id") Long id) {
        final Owner owner = petclinicService.findOwner(id);
        model.addAttribute("owner", owner);
        return "updateOwnerForm";
    }

    @PostMapping("/updateOwner")
    public String updateOwner(Owner owner) {
        petclinicService.updateOwner(owner);
        return "redirect:/owners";
    }
}
