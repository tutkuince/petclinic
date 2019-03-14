package com.muditasoft.petclinic.rest;

import com.muditasoft.petclinic.exception.OwnerNotFoundException;
import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetclinicRestController {

    @Autowired
    private PetclinicService petclinicService;

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petclinicService.findOwners();
        return ResponseEntity.ok(owners);

    }

    @GetMapping("/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("sn") String surname) {
        List<Owner> owners = petclinicService.findOwners(surname);
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        try {
            Owner owner = petclinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }


    }
}
