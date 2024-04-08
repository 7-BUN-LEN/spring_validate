package com.example.homework003.service.serviceimpl;

import com.example.homework003.exception.NotFoundException;
import com.example.homework003.model.Attendee;
import com.example.homework003.model.dto.request.AttendeeRequest;
import com.example.homework003.model.dto.response.AttendeeResponse;
import com.example.homework003.repository.AttendeeRepository;
import com.example.homework003.service.AttendeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    public final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<AttendeeResponse> findAllAttendee(Integer offset, Integer limit){
        offset = (offset-1) * limit;
        List<Attendee> attendees = attendeeRepository.findAllAttendee(offset, limit);
        List<AttendeeResponse> attendeeResponses = new ArrayList<>();
        for(Attendee attendee: attendees){
            AttendeeResponse attendeeResponse = modelMapper.map(attendee, AttendeeResponse.class);
            attendeeResponses.add(attendeeResponse);
        }
        return attendeeResponses;
    }

    @Override
    public AttendeeResponse insert(AttendeeRequest attendeeRequest) {
        Attendee attendeeResponse = attendeeRepository.insert(attendeeRequest);
        return modelMapper.map(attendeeResponse, AttendeeResponse.class);
    }

    @Override
    public AttendeeResponse findAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.findAttendeeById(id);
        if(attendee == null){
            throw  new NotFoundException("The Attendee id " + id + " has not been found");
        }
        return modelMapper.map(attendee, AttendeeResponse.class);
    }

    @Override
    public AttendeeResponse updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) {
       Attendee attendee = attendeeRepository.updateAttendeeById(id,attendeeRequest);
       if(attendee == null){
           throw  new NotFoundException("The Attendee id " + id + " has not been found");
       }
        return modelMapper.map(attendee,AttendeeResponse.class);
    }

    @Override
    public String removeAttendeeById(Integer id) {
        boolean isSuccess = attendeeRepository.removeAttendeeById(id);
        if(!isSuccess){
            throw  new NotFoundException("The Attendee id " + id + " has not been found");
        }
        else {
            return null;
        }

    }


}
