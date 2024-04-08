package com.example.homework003.service;

import com.example.homework003.model.dto.request.EventRequest;
import com.example.homework003.model.dto.response.EventResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EventService {
    List<EventResponse> findAllEvents(Integer offset, Integer limit);

    EventResponse insert(EventRequest eventRequest);

    EventResponse findEventById(Integer id);

    EventResponse updateEventById(Integer id, EventRequest eventRequest);

    String removeEventById(Integer id);
}
