package com.muditasoft.petclinic.model;

import com.muditasoft.petclinic.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner extends BaseEntity {

    @NotEmpty(message = "Surname is mandatory")
    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToMany(mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
