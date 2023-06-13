package com.example.futsalregisterservice.repositories;

import com.example.futsalregisterservice.entities.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FutsalRepo extends JpaRepository<Futsal,Integer>, JpaSpecificationExecutor<Futsal> {
}
