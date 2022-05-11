package com.example.demo.repository;

import com.example.demo.entity.Veicolo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeicoloRepository extends CrudRepository<Veicolo, Long> {

    
}