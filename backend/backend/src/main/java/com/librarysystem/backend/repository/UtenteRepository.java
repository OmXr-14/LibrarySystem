package com.librarysystem.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.librarysystem.backend.model.Utente;


@Repository
public interface UtenteRepository extends MongoRepository<Utente, String>{

}
