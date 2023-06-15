package com.example.futsalregisterservice.entities;

import com.example.futsalregisterservice.enums.FutsalEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "The data is empty")
    @NotNull(message="Data is null")
    private String futsalName;
    @NotBlank(message = "The data is empty")
    @NotNull(message= "Data is null")
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
