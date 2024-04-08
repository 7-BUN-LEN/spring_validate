package com.example.homework003.service.serviceimpl;

import com.example.homework003.exception.NotFoundException;
import com.example.homework003.model.Event;
import com.example.homework003.model.dto.request.EventRequest;
import com.example.homework003.model.dto.request.VenueRequest;
import com.example.homework003.model.dto.response.EventResponse;
import com.example.homework003.model.dto.response.VenueResponse;
import com.example.homework003.repository.EventRepository;
import com.example.homework003.service.AttendeeService;
import com.example.homework003.service.EventService;
import com.example.homework003.service.VenueService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final VenueService venueService;
    private final AttendeeService attendeeService;


     @Override
    public List<EventResponse> findAllEvents(Integer offset, Integer limit){
         offset = (offset - 1) * limit;
         List<Event> events = eventRepository.findAllEvents(offset, limit);
         List<EventResponse> eventResponses = new ArrayList<>();
         for (Event event : events){
             EventResponse eventResponse = modelMapper.map(event,EventResponse.class);
             eventResponses.add(eventResponse);
         }
         return eventResponses;
     }

     @Override
    public EventResponse insert(EventRequest eventRequest){
         venueService.findVenueById(eventRequest.getVenue());
         Event eventResponse = eventRepository.insert(eventRequest);
         for(int i : eventRequest.getAttendee()){
             attendeeService.findAttendeeById(i);
             eventRepository.insertToEventAttendee(eventResponse.getEventId(), i);
         }
         return  findEventById(eventResponse.getEventId());
     }

    @Override
    public EventResponse findEventById(Integer id) {
         Event eventResponse = eventRepository.findEventById(id);
         if(eventResponse == null){
             throw new NotFoundException("The Event id " + id + " has not been found");
         }

        return modelMapper.map(eventResponse, EventResponse.class);
    }

    @Override
    public EventResponse updateEventById(Integer id, EventRequest eventRequest) {
        venueService.findVenueById(id);
        Event eventResponse = eventRepository.updateEventById(id, eventRequest);
        if(eventResponse == null){
            throw new NotFoundException("The Event id " + id + " has not been found");
        }else {
            eventRepository.deleteAttendeeById(id);
            for(Integer evId : eventRequest.getAttendee()){
                attendeeService.findAttendeeById(id);
                eventRepository.insertAttendeeByUser(id, evId);
            }
            return findEventById(id);
        }


    }

    @Override
    public String removeEventById(Integer id){
         boolean isSuccess = eventRepository.removeEventById(id);
         if (!isSuccess){
             throw  new NotFoundException("The Event id " + id + " has not been found");
         }
         else {
             return null;
         }
    }


}
