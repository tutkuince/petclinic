package com.muditasoft.petclinic.service;

import com.muditasoft.petclinic.exception.OwnerNotFoundException;
import com.muditasoft.petclinic.model.Owner;

import java.util.List;

public interface PetclinicService {
    List<Owner> findOwners();
    List<Owner> findOwners(String surname);
    Owner findOwner(Long id) throws OwnerNotFoundException;
    void createOwner(Owner owner);
    void updateOwner(Owner owner);
    void deleteOwner(Long id);
}
