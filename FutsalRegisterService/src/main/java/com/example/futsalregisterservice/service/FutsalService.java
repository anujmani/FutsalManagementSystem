package com.example.futsalregisterservice.service;

import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;

import java.util.List;

public interface FutsalService {
    void addFutsal(FutsalRequestDto futsalRequestDto);

    List<FutsalResponseDto> getAllFutsal();

    FutsalResponseDto getFutsal(int id);
}
