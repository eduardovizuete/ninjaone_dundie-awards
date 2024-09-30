package com.ninjaone.dundie_awards.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(
        description = "ActivityDTO Model information"
)
public class ActivityDTO {

    @Schema(
            description = "Activity Id"
    )
    private Long id;

    @Schema(
            description = "Activity occured at"
    )
    private LocalDateTime occuredAt;

    @Schema(
            description = "Activity event"
    )
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
