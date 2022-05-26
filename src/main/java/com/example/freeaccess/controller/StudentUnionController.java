package com.example.freeaccess.controller;

import com.example.freeaccess.domain.student_union.StudentUnionDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.service.student_union.FindStudentUnion;
import com.example.freeaccess.service.student_union.SaveStudentUnion;
import com.example.freeaccess.service.student_union.UpdateStudentUnion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/school/student-union")
public class StudentUnionController {

    private final SaveStudentUnion save;
    private final UpdateStudentUnion update;
    private final FindStudentUnion find;

    public StudentUnionController(SaveStudentUnion save, UpdateStudentUnion update, FindStudentUnion find) {
        this.save = save;
        this.update = update;
        this.find = find;
    }

    @PostMapping
    public ResponseEntity<StudentUnionDTO> save(@RequestBody StudentUnionDTO studentUnionDTO) throws ObjectLimitException {
        studentUnionDTO = this.save.execute(studentUnionDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentUnionDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(studentUnionDTO);
    }

    @ExceptionHandler({ConstraintViolationException.class, ObjectLimitException.class, NoSuchElementException.class})
    public ResponseEntity<List<String>> constraintViolationExceptionHandler(Exception exception) {
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<StudentUnionDTO> update(@RequestBody StudentUnionDTO studentUnionDTO) {
        studentUnionDTO = this.update.execute(studentUnionDTO);
        return ResponseEntity.ok(studentUnionDTO);
    }

    @GetMapping
    public ResponseEntity<StudentUnionDTO> find() {
        return ResponseEntity.ok(this.find.execute());
    }
}
