package com.example.futsalregisterservice.entities;

import com.example.futsalregisterservice.enums.FutsalEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Futsal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int futsalId;
    private String futsalName;
    private String description;
    @Enumerated(EnumType.STRING)
    private FutsalEnum futsalEnum;
    @OneToMany()
    @Cascade(CascadeType.ALL)
    private List<Contact> contact;
    @OneToOne()
    @Cascade(CascadeType.ALL)
    private Address address;
    private String ownerEmail;

}
