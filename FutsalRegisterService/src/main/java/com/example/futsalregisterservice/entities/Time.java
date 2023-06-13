//package com.example.futsalregisterservice.entities;
//
//import com.example.futsalregisterservice.enums.FutsalEnum;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Time {
//    @Id
//    private int timeID;
//    private String timeRange;
//
//    @Enumerated(EnumType.STRING)
//    private FutsalEnum futsalEnum;
//    @ManyToOne
//    @JsonBackReference
//    private Futsal futsal;
//}
