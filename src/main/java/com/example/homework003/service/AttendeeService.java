package com.example.homework003.service;

import com.example.homework003.model.dto.request.AttendeeRequest;
import com.example.homework003.model.dto.response.AttendeeResponse;

import java.util.List;

public interface AttendeeService {
    List<AttendeeResponse> findAllAttendee(Integer offset, Integer limit);

    AttendeeResponse insert (AttendeeRequest attendeeRequest);

    AttendeeResponse findAttendeeById(Integer id);

    AttendeeResponse updateAttendeeById(Integer id, AttendeeRequest attendeeRequest);

    String removeAttendeeById(Integer id);
}
