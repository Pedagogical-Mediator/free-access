package com.example.freeaccess.service.calendar;

import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.example.freeaccess.domain.calendar.CalendarDTO;
import com.example.freeaccess.repository.CalendarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCalendar {

    private final CalendarRepository repository;
    private final ModelMapper modelMapper;

    public FindCalendar(CalendarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CalendarDTO execute(){
        return this.modelMapper.map(this.repository.findAll().stream().findFirst().orElseThrow(), CalendarDTO.class);
    }
}
