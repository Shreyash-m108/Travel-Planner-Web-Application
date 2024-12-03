package com.travelplanner.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinationDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private Long tripId;
}
