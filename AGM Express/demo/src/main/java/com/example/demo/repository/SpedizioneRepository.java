package com.example.demo.repository;

import com.example.demo.entity.Spedizione;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpedizioneRepository extends CrudRepository<Spedizione, Long> {
}