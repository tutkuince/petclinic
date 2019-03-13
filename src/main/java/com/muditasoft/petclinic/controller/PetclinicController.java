package com.muditasoft.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PetclinicController {

    @GetMapping("/pcs")
    @ResponseBody
    public String welcome() {
        return "Welcome to PetClinic World!";
    }
}
