package com.example.freeaccess.service.calendar;

import com.example.freeaccess.domain.calendar.Calendar;
import com.example.freeaccess.domain.calendar.CalendarDTO;
import com.example.freeaccess.repository.CalendarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateCalendar {

    private final CalendarRepository repository;

    private final ModelMapper modelMapper;

    public UpdateCalendar(CalendarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CalendarDTO execute(CalendarDTO calendarDTO){
        Calendar calendar = this.repository.findAll().stream().findAny().orElse(new Calendar());

        calendarDTO.setId(calendar.getId());
        modelMapper.map(calendarDTO, calendar);

        calendar = this.repository.save(calendar);

        return modelMapper.map(calendar, CalendarDTO.class);
    }

}
