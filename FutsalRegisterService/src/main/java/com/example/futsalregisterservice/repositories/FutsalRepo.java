package com.example.futsalregisterservice.repositories;

import com.example.futsalregisterservice.entities.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FutsalRepo extends JpaRepository<Futsal,Integer> {
}
