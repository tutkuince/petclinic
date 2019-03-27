package com.muditasoft.petclinic.controller;

import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetclinicController {

    @Autowired
    private PetclinicService petclinicService;

    @GetMapping(value = {"/", "/owners"})
    public String getOwners(Model model) {
        model.addAttribute("owners", petclinicService.findOwners());
        return "index";
    }

    @GetMapping("/createOwners")
    public String createOwners() {
        Owner owner1 = new Owner();
        owner1.setName("Tutku");
        owner1.setSurname("Ince");

        Owner owner2 = new Owner();
        owner2.setName("Emin");
        owner2.setSurname("Koklu");

        Owner owner3 = new Owner();
        owner3.setName("Ugur");
        owner3.setSurname("Batikan");

        Owner owner4 = new Owner();
        owner4.setName("Alper");
        owner4.setSurname("Omeroglu");

        petclinicService.createOwner(owner1);
        petclinicService.createOwner(owner2);
        petclinicService.createOwner(owner3);
        petclinicService.createOwner(owner4);

        return "index";
    }
}
