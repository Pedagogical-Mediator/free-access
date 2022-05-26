package com.example.freeaccess.controller;

import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.service.bullying.FindBullying;
import com.example.freeaccess.service.bullying.SaveBullying;
import com.example.freeaccess.service.bullying.UpdateBullying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/school/bullying")
public class BullyingController {

    private final SaveBullying saveBullying;
    private final UpdateBullying updateBullying;
    private final FindBullying findBullying;

    public BullyingController(SaveBullying saveBullying, UpdateBullying updateBullying, FindBullying findBullying) {
        this.saveBullying = saveBullying;
        this.updateBullying = updateBullying;
        this.findBullying = findBullying;
    }

    @PostMapping
    public ResponseEntity<BullyingDTO> save(@RequestBody @Valid BullyingDTO bullyingDTO) throws ObjectLimitException {
        bullyingDTO = this.saveBullying.execute(bullyingDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bullyingDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(bullyingDTO);
    }

    @ExceptionHandler({ConstraintViolationException.class, ObjectLimitException.class, NoSuchElementException.class})
    public ResponseEntity<List<String>> constraintViolationExceptionHandler(Exception exception){
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<BullyingDTO> update(@RequestBody BullyingDTO bullyingDTO) {
        bullyingDTO = this.updateBullying.execute(bullyingDTO);
        return ResponseEntity.ok(bullyingDTO);
    }

    @GetMapping
    public ResponseEntity<BullyingDTO> find() {
        return ResponseEntity.ok(this.findBullying.execute());
    }
}
