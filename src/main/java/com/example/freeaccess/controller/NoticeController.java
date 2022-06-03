package com.example.freeaccess.controller;


import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.service.notice.SaveNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

   @Autowired
   private SaveNotice save;

    @PostMapping
    public ResponseEntity<NoticeDTO> save (@Valid @RequestBody NoticeDTO noticeDTO) {
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


//    @GetMapping(value = "/{id}")
//    public ResponseEntity<AvisoDTO> findById(@PathVariable Integer id){
//        Aviso aviso = avisoService.findById(id);
//        AvisoDTO avisoDTO = modelMapper.modelMapper().map(aviso, AvisoDTO.class);
//
//        return ResponseEntity.ok().body(avisoDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<AvisoDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, @RequestParam(value="linesPerPage", defaultValue="10") Integer linesPerPage, @RequestParam(value="orderBy", defaultValue="dataDeCriacao") String orderBy, @RequestParam(value="direction", defaultValue="DESC") String direction) {
//
//        Page<Aviso> pagina = avisoService.findPage(page, linesPerPage, orderBy, direction);
//
//        List<AvisoDTO> dtos = pagina.stream().map(aviso -> modelMapper.modelMapper().map(aviso, AvisoDTO.class))
//                .collect(Collectors.toList());
//
//        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
//
//
//        Page<AvisoDTO> paginaDTO = new PageImpl<>(dtos, pageRequest, dtos.size());
//
//        return ResponseEntity.ok().body(paginaDTO);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<AvisoDTO> update (@PathVariable Integer id, @RequestBody AvisoDTO avisoDTO) throws CampoObrigatorio, TamanhoDeCampoExcedente {
//
//        avisoDTO.setId(id);
//
//        AvisoDTO avisoRetornado = modelMapper.modelMapper().map(avisoService.update(modelMapper.modelMapper().map(avisoDTO, Aviso.class)), AvisoDTO.class);
//
//        return ResponseEntity.ok().body(avisoRetornado);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete (@PathVariable Integer id){
//
//        avisoService.delete(id);
//
//        return ResponseEntity.ok().build();
//    }
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
