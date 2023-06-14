package com.example.futsalregisterservice.dto;

import com.example.futsalregisterservice.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private int futsalId;
    private List<Contact> contacts;
}
