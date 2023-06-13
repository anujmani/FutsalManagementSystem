package com.example.futsalregisterservice.controller;

import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;
import com.example.futsalregisterservice.entities.Futsal;
import com.example.futsalregisterservice.service.FutsalService;
import org.springframework.data.domain.Pageable;
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
    private void register(@RequestBody FutsalRequestDto futsalRequestDto,@RequestHeader("Authorization") String authorizationHeader){
        futsalService.addFutsal(futsalRequestDto,authorizationHeader);
    }
    @GetMapping("/getAllRegisteredFutsal")
    private ResponseEntity<List<FutsalResponseDto>> getAllFutsal(){
        return ResponseEntity.ok(futsalService.getAllFutsal());
    }
    @GetMapping("/getFutsal/{id}")
    private ResponseEntity<FutsalResponseDto>getFutsal(@PathVariable("id") int id){
        return ResponseEntity.ok(futsalService.getFutsal(id));
    }
    @PostMapping("/updateStatus/{id}")
    private String updateFutsal(@PathVariable("id") int id){
        return futsalService.updateStatus(id);
    }
    @GetMapping("/specified")
    public List<Futsal> getSpecifiedFutsal(@RequestParam(required = false) String futsalName,
                                           @RequestParam(required = false) String contact,
                                           @RequestParam(required = false) String address,
                                           Pageable pageable){
        return futsalService.getSearchFutsal(futsalName,contact,address,pageable);
//        Specification<Futsal> specification= new Specification<Futsal>() {
//            @Override
//            public Predicate toPredicate(Root<Futsal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return criteriaBuilder.equal(root.get("futsalName"),"Anuj");
//            }
//        };

    }

}
