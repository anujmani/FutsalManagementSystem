package com.example.bookingservice.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Futsal {
    private int futsalId;
    private String futsalName;
    private String description;
    private FutsalEnum futsalEnum;
    private List<Contact> contact;
    private Address address;
}
