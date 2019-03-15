package com.muditasoft.petclinic.rest;

import com.muditasoft.petclinic.model.Owner;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetclinicRestControllerTest {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGetOwnerById() {
        final ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Tutku"));
    }

    @Test
    public void testGetOwnersBySurname() {
        final ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?sn=Ince", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();
        final List<String> nameList = body.stream().map(o -> o.get("name")).collect(Collectors.toList());
        MatcherAssert.assertThat(nameList, Matchers.containsInAnyOrder("Tutku"));
    }

    @Test
    public void testGetOwners() {
        final ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();
        final List<String> nameList = body.stream().map(o -> o.get("name")).collect(Collectors.toList());
        MatcherAssert.assertThat(nameList, Matchers.containsInAnyOrder("Tutku", "Emin", "Ugur", "Alper"));
    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setName("Utku");
        owner.setSurname("Ince");

        final URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);

        Owner owner1 = restTemplate.getForObject(location, Owner.class);

        MatcherAssert.assertThat(owner1.getName(), Matchers.equalTo(owner.getName()));
        MatcherAssert.assertThat(owner1.getSurname(), Matchers.equalTo(owner.getSurname()));
    }

    @Test
    public void testUpdateOwner() {
        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);

        MatcherAssert.assertThat(owner.getName(), Matchers.equalTo("Emin"));

        owner.setSurname("Ince");
        restTemplate.put("http://localhost:8080/rest/owner/2", owner);

        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/2", Owner.class);

        MatcherAssert.assertThat(owner.getSurname(), Matchers.equalTo("Ince"));
    }

    @Test
    public void testDeleteOwner() {
        restTemplate.delete("http://localhost:8080/rest/owner/4");

        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/4", Owner.class);
            Assertions.fail("should have not returned owner!");
        } catch (RestClientException e) {

        }
    }
}
