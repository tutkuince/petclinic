package com.muditasoft.petclinic.dao;

import com.muditasoft.petclinic.model.Owner;

import java.util.List;

public interface OwnerRepository {
    List<Owner> findAll();
    Owner findById(Long id);
    List<Owner> findBySurname(String surname);
    void create(Owner owner);
    Owner update(Owner owner);
}
