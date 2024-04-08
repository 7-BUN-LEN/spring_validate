package com.example.homework003.service.serviceimpl;

import com.example.homework003.exception.NotFoundException;
import com.example.homework003.model.Venue;
import com.example.homework003.model.dto.request.VenueRequest;
import com.example.homework003.model.dto.response.VenueResponse;
import com.example.homework003.repository.VenueRepository;
import com.example.homework003.service.VenueService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<VenueResponse> findAllVenue(Integer offset, Integer limit) {
        offset = (offset-1) * limit;
        List<Venue> venues = venueRepository.findAllVenue(offset,limit);
        List<VenueResponse> venueResponses = new ArrayList<>();
        for (Venue venue : venues){
            VenueResponse venueResponse = modelMapper.map(venue, VenueResponse.class);
            venueResponses.add(venueResponse);
        }
        return venueResponses;
    }


    @Override
    public VenueResponse insert(VenueRequest venueRequest){
        Venue venueResponse = venueRepository.insert(venueRequest);
         return modelMapper.map(venueResponse, VenueResponse.class);
    }

    @Override
    public VenueResponse findVenueById(Integer id){
        Venue venue = venueRepository.findVenueById(id);
        if(venue == null){
            throw new NotFoundException("The Venue id " + id + " has not been found");
        }
        return modelMapper.map(venue, VenueResponse.class);
    }

    @Override
    public VenueResponse updateVenueById(Integer id, VenueRequest venueRequest){
        Venue venueResponse = venueRepository.updateVenueById(id,venueRequest);
        if(venueResponse == null){
            throw  new NotFoundException("The Venue id " + id + " has not been found");
        }
        return modelMapper.map(venueResponse, VenueResponse.class);
    }

    @Override
    public String removeVenueById(Integer id) {
        boolean isSuccess = venueRepository.removeVenueById(id);
        if(!isSuccess){
            throw  new NotFoundException("The Venue id " + id + " has not been found");

        }else {
             return null;
        }
    }
}
