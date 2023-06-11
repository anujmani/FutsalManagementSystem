package com.example.futsalregisterservice.service;

import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;
import com.example.futsalregisterservice.entities.Contact;
import com.example.futsalregisterservice.entities.Futsal;
import com.example.futsalregisterservice.exception.ResourceNotFoundException;
import com.example.futsalregisterservice.repositories.FutsalRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FutsalServiceImpl implements FutsalService{

    private final FutsalRepo futsalRepo;

    public FutsalServiceImpl(FutsalRepo futsalRepo) {
        this.futsalRepo = futsalRepo;
    }

    @Override
    public void addFutsal(FutsalRequestDto futsalRequestDto) {
        Futsal futsal = new Futsal();
        futsal.setFutsalName(futsalRequestDto.getFutsalName());
        futsal.setContact(futsalRequestDto.getContact());
        futsal.setAddress(futsalRequestDto.getAddress());
        futsal.setDescription(futsalRequestDto.getDescription());
        futsal.setFutsalEnum(futsalRequestDto.getFutsalEnum());

        futsalRepo.save(futsal);

    }

    @Override
    public List<FutsalResponseDto> getAllFutsal() {
        List<Futsal>futsalList=futsalRepo.findAll();
        List<FutsalResponseDto>futsalResponseDtoList= new ArrayList<>();
        for(Futsal futsal:futsalList){
            FutsalResponseDto futsalResponseDto= new FutsalResponseDto();
            futsalResponseDto.setFutsalId(futsal.getFutsalId());
            futsalResponseDto.setFutsalName(futsal.getFutsalName());
            futsalResponseDto.setDescription(futsal.getDescription());
            futsalResponseDtoList.add(futsalResponseDto);
        }
        return futsalResponseDtoList;
    }

    @Override
    public FutsalResponseDto getFutsal(int id) {
        Futsal futsal= futsalRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no Futsal registered this named"));
        FutsalResponseDto futsalResponseDto= new FutsalResponseDto();
        futsalResponseDto.setFutsalName(futsal.getFutsalName());
        futsalResponseDto.setDescription(futsal.getDescription());
        futsalResponseDto.setFutsalId(futsal.getFutsalId());

        return futsalResponseDto;
    }
}
