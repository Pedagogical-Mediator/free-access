package com.example.freeaccess.repository;


import com.example.freeaccess.domain.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
}
