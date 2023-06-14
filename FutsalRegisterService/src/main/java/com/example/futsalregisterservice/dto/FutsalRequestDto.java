package com.example.futsalregisterservice.dto;

import com.example.futsalregisterservice.entities.Address;
import com.example.futsalregisterservice.entities.Contact;
import com.example.futsalregisterservice.enums.FutsalEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FutsalRequestDto {
    private int futsalId;
    private String futsalName;
    private String description;
    private FutsalEnum futsalEnum;
    private List<Contact> contact;
    private Address address;
    private String image;

}
