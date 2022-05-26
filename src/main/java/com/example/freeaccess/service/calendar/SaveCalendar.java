package com.example.freeaccess.service.calendar;

import com.example.freeaccess.domain.calendar.Calendar;
import com.example.freeaccess.domain.calendar.CalendarDTO;
import com.example.freeaccess.exceptions.ObjectLimitException;
import com.example.freeaccess.repository.CalendarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SaveCalendar {

    private final ModelMapper modelMapper;

    private final CalendarRepository repository;

    public SaveCalendar(ModelMapper modelMapper, CalendarRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public CalendarDTO execute(CalendarDTO calendarDTO) throws ObjectLimitException {
        if (!this.repository.findAll().isEmpty()) {
            throw new ObjectLimitException("The number of bullying information allowed is only one. If you want to update the information, send a PUT");
        }

        Calendar calendar = modelMapper.map(calendarDTO, Calendar.class);

        calendar = this.repository.save(calendar);

        return modelMapper.map(calendar, CalendarDTO.class);
    }
}
