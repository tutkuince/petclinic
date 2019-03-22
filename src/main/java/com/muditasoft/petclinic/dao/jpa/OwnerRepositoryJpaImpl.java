package com.muditasoft.petclinic.dao.jpa;

import com.muditasoft.petclinic.dao.OwnerRepository;
import com.muditasoft.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OwnerRepositoryJpaImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public OwnerRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner", Owner.class).getResultList();
    }

    @Override
    public Owner findById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public List<Owner> findBySurname(String surname) {
        return entityManager.createQuery("from Owner where surname =:surname", Owner.class)
                .setParameter("surname", surname)
                .getResultList();
    }

    @Override
    public void create(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return entityManager.merge(owner);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Owner.class, id));
    }
}
