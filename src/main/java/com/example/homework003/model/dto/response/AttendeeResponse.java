package com.example.homework003.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeResponse {
    private Integer attendeeId;
    private String attendeeName;
    private String email;

}
