package com.example.demo.repository;
import com.example.demo.entity.Magazzino;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazzinoRepository extends CrudRepository<Magazzino, Long> {
}