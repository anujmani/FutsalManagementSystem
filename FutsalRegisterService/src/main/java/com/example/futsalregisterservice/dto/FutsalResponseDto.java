package com.example.futsalregisterservice.dto;

import com.example.futsalregisterservice.enums.FutsalEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FutsalResponseDto {
    private int futsalId;
    private String futsalName;
    private String description;
    private FutsalEnum futsalEnum;
}
