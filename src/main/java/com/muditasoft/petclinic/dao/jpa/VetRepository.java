package com.muditasoft.petclinic.dao.jpa;

import com.muditasoft.petclinic.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Long> {

}
