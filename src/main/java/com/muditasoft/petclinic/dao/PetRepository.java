package com.muditasoft.petclinic.dao;


import com.muditasoft.petclinic.model.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findAll();
    Pet findById(Long id);
    List<Pet> findByOwnerId(Long id);
    void create(Pet pet);
    Pet update(Pet pet);
    void delete(Long id);
    void deleteByOwnerId(Long id);
}
