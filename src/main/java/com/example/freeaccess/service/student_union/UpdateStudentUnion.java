package com.example.freeaccess.service.student_union;

import com.example.freeaccess.domain.student_union.StudentUnion;
import com.example.freeaccess.domain.student_union.StudentUnionDTO;
import com.example.freeaccess.repository.StudentUnionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateStudentUnion {

    private final StudentUnionRepository repository;
    private final ModelMapper modelMapper;

    public UpdateStudentUnion(StudentUnionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public StudentUnionDTO execute(StudentUnionDTO studentUnionDTO) {
        StudentUnion studentUnion = this.repository.findAll().stream().findAny().orElse(new StudentUnion());

        studentUnionDTO.setId(studentUnion.getId());
        this.modelMapper.map(studentUnionDTO, studentUnion);

        studentUnion = this.repository.save(studentUnion);

        return this.modelMapper.map(studentUnion, StudentUnionDTO.class);
    }
}
