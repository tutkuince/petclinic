package com.muditasoft.petclinic.controller;

import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String createNewOwnerForm(@Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "createOwnerForm";
        petclinicService.createOwner(owner);
        redirectAttributes.addFlashAttribute("message", "Owner created with id " + owner.getId());
        return "redirect:/owners";
    }

    @GetMapping("/updateForm")
    public String updateOwnerForm(Model model, @RequestParam("id") Long id) {
        final Owner owner = petclinicService.findOwner(id);
        model.addAttribute("owner", owner);
        return "updateOwnerForm";
    }

    @PostMapping("/updateOwner")
    public String updateOwner(@Valid Owner owner, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "updateOwnerForm";

        petclinicService.updateOwner(owner);
        redirectAttributes.addFlashAttribute("message", "Owner updated with id " + owner.getId());
        return "redirect:/owners";
    }

    @GetMapping("/delete")
    public String deleteOwner(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        petclinicService.deleteOwner(id);
        redirectAttributes.addFlashAttribute("message", "Owner deleted with id " + id);
        return "redirect:/owners";
    }
}
