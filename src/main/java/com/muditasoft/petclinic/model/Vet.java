package com.muditasoft.petclinic.model;

import com.muditasoft.petclinic.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vet")
public class Vet extends BaseEntity {

    @NotEmpty
    @Column(name = "surname")
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
