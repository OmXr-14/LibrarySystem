package com.librarysystem.backend.repository;

import org.springframework.stereotype.Repository;
import com.librarysystem.backend.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> {

    List<Libro> findByDisponibileTrue();
} 
