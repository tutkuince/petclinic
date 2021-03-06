package com.muditasoft.petclinic.rest;

import com.muditasoft.petclinic.exception.InternalSeverException;
import com.muditasoft.petclinic.exception.OwnerNotFoundException;
import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

//    @GetMapping("/owner/{id}")
//    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
//        try {
//            Owner owner = petclinicService.findOwner(id);
//            return ResponseEntity.ok(owner);
//        } catch (OwnerNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // After creation process, return owner's uri
    @PostMapping("/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petclinicService.createOwner(owner);
            Long id = owner.getId();
            final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petclinicService.findOwner(id);
            owner.setName(ownerRequest.getName());
            owner.setSurname(ownerRequest.getSurname());
            petclinicService.updateOwner(owner);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        try {
            petclinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException e) {
            throw new OwnerNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new InternalSeverException(e);
        }
    }

    @GetMapping(produces = "application/json", value = "/owner/{id}")
    public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id) {
        try {
            Owner owner = petclinicService.findOwner(id);
            Link self = ControllerLinkBuilder.linkTo(PetclinicRestController.class).slash("/owner/" + id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(PetclinicRestController.class).slash("/owner").withRel("create");
            Link update = ControllerLinkBuilder.linkTo(PetclinicRestController.class).slash("/owner/" + id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(PetclinicRestController.class).slash("/owner/" + id).withRel("delete");

            Resource<Owner> resource = new Resource<>(owner, self, create, update, delete);

            return ResponseEntity.ok(resource);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
