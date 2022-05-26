package com.example.freeaccess.service.student_union;

import com.example.freeaccess.domain.student_union.StudentUnionDTO;
import com.example.freeaccess.repository.StudentUnionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FindStudentUnion {

    private final StudentUnionRepository repository;
    private final ModelMapper modelMapper;

    public FindStudentUnion(StudentUnionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public StudentUnionDTO execute(){
        return this.modelMapper.map(this.repository.findAll().stream().findFirst().orElseThrow(), StudentUnionDTO.class);
    }

}
