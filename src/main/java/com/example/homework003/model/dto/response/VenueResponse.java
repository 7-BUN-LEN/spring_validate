package com.example.homework003.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponse {
    private Integer venueId;
    private String venueName;
    private String location;
}
