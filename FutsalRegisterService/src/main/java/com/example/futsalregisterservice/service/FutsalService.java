package com.example.futsalregisterservice.service;

import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;
import com.example.futsalregisterservice.entities.Futsal;

import java.awt.print.Pageable;
import java.util.List;

public interface FutsalService {
    void addFutsal(FutsalRequestDto futsalRequestDto,String authHeader);

    List<FutsalResponseDto> getAllFutsal();

    FutsalResponseDto getFutsal(int id);

    String updateStatus(int id);



    List<Futsal> getSearchFutsal(String futsalName, String contact, String address,
                                 org.springframework.data.domain.Pageable pageable);
}
