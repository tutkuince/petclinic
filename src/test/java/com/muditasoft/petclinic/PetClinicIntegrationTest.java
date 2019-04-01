package com.muditasoft.petclinic;

import com.muditasoft.petclinic.model.Owner;
import com.muditasoft.petclinic.service.PetclinicService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=dev"})
public class PetClinicIntegrationTest {

    @Autowired
    private PetclinicService petclinicService;


    @Test
    public void testFindOwners() {
        final List<Owner> owners = petclinicService.findOwners();
        MatcherAssert.assertThat(owners.size(), Matchers.equalTo(4));
    }
}
