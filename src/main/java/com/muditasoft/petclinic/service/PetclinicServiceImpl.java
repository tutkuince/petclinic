package com.muditasoft.petclinic.service;

import com.muditasoft.petclinic.dao.OwnerRepository;
import com.muditasoft.petclinic.exception.OwnerNotFoundException;
import com.muditasoft.petclinic.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetclinicServiceImpl implements PetclinicService {

    private OwnerRepository ownerRepository;

    @Autowired
    public PetclinicServiceImpl(@Qualifier("ownerRepositoryJpaImpl") OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Secured(value = {"ROLE_USER", "ROLE_EDITOR"})
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Owner> findOwners(String surname) {
        return ownerRepository.findBySurname(surname);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
