package com.muditasoft.petclinic.dao.jpa;

import com.muditasoft.petclinic.dao.OwnerRepository;
import com.muditasoft.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OwnerRepositoryJpaImpl implements OwnerRepository {

    @Override
    public List<Owner> findAll() {
        return null;
    }

    @Override
    public Owner findById(Long id) {
        return null;
    }

    @Override
    public List<Owner> findBySurname(String surname) {
        return null;
    }

    @Override
    public void create(Owner owner) {

    }

    @Override
    public Owner update(Owner owner) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
