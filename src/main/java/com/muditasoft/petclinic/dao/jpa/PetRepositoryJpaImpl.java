package com.muditasoft.petclinic.dao.jpa;

import com.muditasoft.petclinic.dao.PetRepository;
import com.muditasoft.petclinic.model.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PetRepositoryJpaImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PetRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Pet> findAll() {
        return entityManager.createQuery("from Pet", Pet.class).getResultList();
    }

    @Override
    public Pet findById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public List<Pet> findByOwnerId(Long id) {
        return entityManager.createQuery("from Pet where owner.id =: id", Pet.class).setParameter("id", id).getResultList();
    }

    @Override
    public void create(Pet pet) {
        entityManager.persist(pet);
    }

    @Override
    public Pet update(Pet pet) {
        return entityManager.merge(pet);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Pet.class, id));
    }

    @Override
    public void deleteByOwnerId(Long id) {
        entityManager.createQuery("delete from Pet where owner.id =: id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
