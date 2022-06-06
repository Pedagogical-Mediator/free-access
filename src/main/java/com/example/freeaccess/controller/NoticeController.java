package com.example.freeaccess.controller;


import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.service.notice.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/school/notice")
public class NoticeController {

    private final SaveNotice save;
    private final DeleteNotice delete;
    private final FindNoticeById findById;
    private final FindAllNotices findAllNotices;
    private final UpdateNotice updateNotice;

    public NoticeController(SaveNotice save, DeleteNotice delete, FindNoticeById findById, FindAllNotices findAllNotices, UpdateNotice updateNotice) {
        this.save = save;
        this.delete = delete;
        this.findById = findById;
        this.findAllNotices = findAllNotices;
        this.updateNotice = updateNotice;
    }

    @PostMapping
    public ResponseEntity<NoticeDTO> save(@Valid @RequestBody NoticeDTO noticeDTO) {
        noticeDTO = this.save.execute(noticeDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(noticeDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(noticeDTO);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<String>> constraintViolationExceptionHandler(ConstraintViolationException exception) {
        return ResponseEntity.badRequest().body(exception.getConstraintViolations().stream().map(constraintViolation -> String.format("%s %s", constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).toList());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<String> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NoticeDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.findById.execute(id));
    }

    @GetMapping
    public ResponseEntity<Page<NoticeDTO>> findPage(@PageableDefault(sort = "creationDate", page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(this.findAllNotices.execute(pageable));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NoticeDTO> update(@PathVariable Integer id, @RequestBody NoticeDTO noticeDTO) {
        noticeDTO.setId(id);
        return ResponseEntity.ok().body(this.updateNotice.execute(noticeDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.delete.execute(id);
        return ResponseEntity.ok().build();
    }
//
//    @GetMapping(value = "/todos")
//    public ResponseEntity<List<AvisoDTO>> findAll() {
//        List<Aviso> avisos = avisoService.findAll();
//
//        List<AvisoDTO> avisosDTO = avisos.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok().body(avisosDTO);
//    }
}
