package com.example.freeaccess.service.student_union;

import com.example.freeaccess.domain.student_union.StudentUnion;
import com.example.freeaccess.domain.student_union.StudentUnionDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.repository.StudentUnionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaveStudentUnion {

    private final StudentUnionRepository repository;
    private final ModelMapper modelMapper;

    public SaveStudentUnion(StudentUnionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public StudentUnionDTO execute(StudentUnionDTO studentUnionDTO) throws ObjectLimitException {
        if (!this.repository.findAll().isEmpty()) {
            throw new ObjectLimitException("The number of student union information allowed is only one. If you want to update the information, send a PUT");
        }

        StudentUnion studentUnion = modelMapper.map(studentUnionDTO, StudentUnion.class);

        studentUnion = this.repository.save(studentUnion);

        return modelMapper.map(studentUnion, StudentUnionDTO.class);
    }

}
