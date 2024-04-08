package com.example.homework003.model.dto.response;

import com.example.homework003.model.Attendee;
import com.example.homework003.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venueId;
    private List<Attendee> attendeeId;
}
