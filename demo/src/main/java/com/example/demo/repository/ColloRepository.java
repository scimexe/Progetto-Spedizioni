package com.example.demo.repository;

import com.example.demo.entity.Collo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColloRepository extends CrudRepository<Collo, Long> {

}