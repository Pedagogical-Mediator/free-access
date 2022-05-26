package com.example.freeaccess.domain.calendar;

public class CalendarDTO {
    private Integer id;
    private String url;

    public CalendarDTO() {
    }

    public CalendarDTO(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        CalendarDTO calendarDTO = (CalendarDTO) obj;
        return this.id.equals(calendarDTO.getId()) && this.url.equals(calendarDTO.getUrl());
    }
}
