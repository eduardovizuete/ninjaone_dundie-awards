package com.ninjaone.dundie_awards.dto;

import java.time.LocalDateTime;

public class ActivityDTO {

    private Long id;

    private LocalDateTime occuredAt;

    private String event;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, LocalDateTime occuredAt, String event) {
        this.id = id;
        this.occuredAt = occuredAt;
        this.event = event;
    }

    public ActivityDTO(LocalDateTime localDateTime, String event) {
        this.occuredAt = localDateTime;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOccuredAt() {
        return occuredAt;
    }

    public String getEvent() {
        return event;
    }

}
