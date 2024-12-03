package com.travelplanner.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private String time;
    private Double cost;
    private Long destinationId;
}