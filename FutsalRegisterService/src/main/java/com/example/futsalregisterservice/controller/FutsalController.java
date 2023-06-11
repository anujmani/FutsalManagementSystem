package com.example.futsalregisterservice.controller;

import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;
import com.example.futsalregisterservice.service.FutsalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/futsal")
public class FutsalController {

    private final FutsalService futsalService;

    public FutsalController(FutsalService futsalService) {
        this.futsalService = futsalService;
    }

    @PostMapping("/registerFutsal")
    private void register(@RequestBody FutsalRequestDto futsalRequestDto){
        futsalService.addFutsal(futsalRequestDto);
    }
    @GetMapping("/getAllRegisteredFutsal")
    private ResponseEntity<List<FutsalResponseDto>> getAllFutsal(){
        return ResponseEntity.ok(futsalService.getAllFutsal());
    }
    @GetMapping("/getFutsal/{id}")
    private ResponseEntity<FutsalResponseDto>getFutsal(@PathVariable("id") int id){
        return ResponseEntity.ok(futsalService.getFutsal(id));
    }


}
