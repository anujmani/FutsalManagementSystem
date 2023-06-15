package com.example.futsalregisterservice.service;

import com.example.futsalregisterservice.dto.ContactDto;
import com.example.futsalregisterservice.dto.FutsalRequestDto;
import com.example.futsalregisterservice.dto.FutsalResponseDto;
import com.example.futsalregisterservice.entities.Contact;
import com.example.futsalregisterservice.entities.Futsal;
import com.example.futsalregisterservice.enums.FutsalEnum;
import com.example.futsalregisterservice.exception.ResourceNotFoundException;
import com.example.futsalregisterservice.repositories.FutsalRepo;
import com.example.futsalregisterservice.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FutsalServiceImpl implements FutsalService {

    private final FutsalRepo futsalRepo;
    private final JwtUtils jwtUtils;
    @Autowired
    private ObjectMapper objectMapper;

    public FutsalServiceImpl(FutsalRepo futsalRepo, JwtUtils jwtUtils) {
        this.futsalRepo = futsalRepo;

        this.jwtUtils = jwtUtils;
    }

    @Override
    public void addFutsal(FutsalRequestDto futsalRequestDto, String authHeader) {
        Futsal futsal = new Futsal();
        futsal.setFutsalName(futsalRequestDto.getFutsalName());
        futsal.setContact(futsalRequestDto.getContact());
        futsal.setAddress(futsalRequestDto.getAddress());
        futsal.setDescription(futsalRequestDto.getDescription());
        futsal.setFutsalEnum(futsalRequestDto.getFutsalEnum());
        String token = authHeader.replace("Bearer ", "");
//        String relativePath = path + "images\\" + futsalRequestDto.getFutsalName().replaceAll("\\s", "");
//        // returns the current working directory of user
//        String absolutePath = System.getProperty("user.dir") + File.separator + relativePath;
//
//        File filePath = new File(absolutePath);
//        if (!filePath.exists()) {
//            filePath.mkdirs();
//        }
//        Files.copy(multipartFiles.getInputStream(), Paths.get(absolutePath,multipartFiles.getName()));
//        futsal.setImage(relativePath+File.separator+multipartFiles.getOriginalFilename());

        String role = jwtUtils.extractUser(token);
        futsal.setOwnerEmail(role);
        log.info("Futsal has been created");
        futsalRepo.save(futsal);

    }

    @Override
    public List<FutsalResponseDto> getAllFutsal() {
        List<Futsal> futsalList = futsalRepo.findAll();
        List<FutsalResponseDto> futsalResponseDtoList = new ArrayList<>();
        for (Futsal futsal : futsalList) {
            FutsalResponseDto futsalResponseDto = new FutsalResponseDto();
            futsalResponseDto.setFutsalId(futsal.getFutsalId());
            futsalResponseDto.setFutsalName(futsal.getFutsalName());
            futsalResponseDto.setDescription(futsal.getDescription());
            futsalResponseDtoList.add(futsalResponseDto);
        }
        log.info("All the futsal has been retrieved");
        return futsalResponseDtoList;
    }

    //To get the futsal by id
    @Override
    public FutsalResponseDto getFutsal(int id) {
        Futsal futsal = futsalRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no Futsal " +
                "registered this id"));
        FutsalResponseDto futsalResponseDto = new FutsalResponseDto();
        futsalResponseDto.setFutsalName(futsal.getFutsalName());
        futsalResponseDto.setDescription(futsal.getDescription());
        futsalResponseDto.setFutsalId(futsal.getFutsalId());
        futsalResponseDto.setFutsalEnum(futsal.getFutsalEnum());

        return futsalResponseDto;
    }

    @Override
    public String updateStatus(int id) {
        Futsal futsal = futsalRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        futsal.setFutsalEnum(FutsalEnum.BOOKED);
        futsalRepo.save(futsal);
        return "Booked";
    }

    @Override
    public List<Futsal> getSearchFutsal(String futsalName, String contact, String address, Pageable pageable) {
        List<Futsal> futsalList = futsalRepo.findAll(new Specification<Futsal>() {
            @Override
            public Predicate toPredicate(Root<Futsal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (Objects.nonNull(futsalName) && Objects.nonNull(address)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("futsalName"),
                            futsalName, address));
                }
                if (!StringUtils.isEmpty(futsalName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("futsalName"),
                            "%" + futsalName + "%"));

                }
                query.orderBy(criteriaBuilder.desc(root.get("futsalName")), criteriaBuilder.asc(root.get("futsalId")));
                return predicate;
            }
        }, pageable).getContent();
        return futsalList;
    }

    @Override
    public void updateFutsal(ContactDto contactDto) {
        List<Contact> contacts = futsalRepo.findById(contactDto.getFutsalId()).get().getContact();
        List<Contact> newContacts = contactDto.getContacts();
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(i);

            for (int j = 0; j < newContacts.size(); j++) {
                if (i == j) {
                    contacts.get(i).setPhoneno(newContacts.get(i).getPhoneno());
                }

            }
        }
        Futsal futsal = futsalRepo.findById(contactDto.getFutsalId()).orElseThrow(null);
        futsal.setContact(contacts);
        futsalRepo.save(futsal);
    }

    public FutsalResponseDto updateFutsalAll(FutsalRequestDto requestDto) {
        Futsal changedfutsal = objectMapper.convertValue(requestDto, Futsal.class);

        Futsal futsal =
                futsalRepo.findById(requestDto.getFutsalId()).orElseThrow(() -> new ResourceNotFoundException());
        futsal.setFutsalName(changedfutsal.getFutsalName());
        futsal.setDescription(changedfutsal.getDescription());
        futsal.setOwnerEmail(changedfutsal.getOwnerEmail());
        FutsalResponseDto futsalResponseDto = objectMapper.convertValue(futsal, FutsalResponseDto.class);
        log.info("Futsal has been updated");
        return futsalResponseDto;
    }
}
