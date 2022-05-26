package com.example.freeaccess.controller;


import com.example.freeaccess.domain.calendar.CalendarDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.service.calendar.FindCalendar;
import com.example.freeaccess.service.calendar.SaveCalendar;
import com.example.freeaccess.service.calendar.UpdateCalendar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/school/calendar")
public class CalendarioController {

    private final SaveCalendar saveCalendar;
    private final UpdateCalendar updateCalendar;
    private final FindCalendar findCalendar;

    public CalendarioController(SaveCalendar saveCalendar, UpdateCalendar updateCalendar, FindCalendar findCalendar) {
        this.saveCalendar = saveCalendar;
        this.updateCalendar = updateCalendar;
        this.findCalendar = findCalendar;
    }

    @PostMapping
    public ResponseEntity<CalendarDTO> save(@RequestBody CalendarDTO calendarDTO) throws ObjectLimitException {
        calendarDTO = this.saveCalendar.execute(calendarDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(calendarDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(calendarDTO);
    }

    @ExceptionHandler({ConstraintViolationException.class, ObjectLimitException.class, NoSuchElementException.class})
    public ResponseEntity<List<String>> constraintViolationExceptionHandler(Exception exception){
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<CalendarDTO> update(@RequestBody CalendarDTO calendarDTO) {
        return ResponseEntity.ok(this.updateCalendar.execute(calendarDTO));
    }

    @GetMapping
    public ResponseEntity<CalendarDTO> find () {
        CalendarDTO calendarDTO = this.findCalendar.execute();
        return ResponseEntity.ok(calendarDTO);
    }
}