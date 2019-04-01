package com.muditasoft.petclinic.dao;

import com.muditasoft.petclinic.model.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class OwnerRepositoryTests {

    @Autowired
    @Qualifier("ownerRepositoryJpaImpl")
    private OwnerRepository ownerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = PersistenceException.class)
    public void testCreateOwner() {
        final Owner owner = new Owner();
        owner.setName(null);
        owner.setSurname(null);

        ownerRepository.create(owner);

        entityManager.flush();
    }
}
