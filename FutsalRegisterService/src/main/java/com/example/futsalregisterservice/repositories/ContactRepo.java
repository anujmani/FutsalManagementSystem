package com.example.futsalregisterservice.repositories;

import com.example.futsalregisterservice.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact,Integer> {
}
