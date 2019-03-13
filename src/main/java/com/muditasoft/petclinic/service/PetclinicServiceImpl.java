package com.muditasoft.petclinic.service;

import com.muditasoft.petclinic.dao.OwnerRepository;
import com.muditasoft.petclinic.exception.OwnerNotFoundException;
import com.muditasoft.petclinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetclinicServiceImpl implements PetclinicService {

    private OwnerRepository ownerRepository;

    @Autowired
    public PetclinicServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwners(String surname) {
        return ownerRepository.findBySurname(surname);
    }

    @Override
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);

        if (owner == null) {
            throw new OwnerNotFoundException("Owner not found with id: " + id);
        }

        return owner;
    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.create(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.delete(id);
    }
}
