package com.example.homework003.service;

import com.example.homework003.model.dto.request.VenueRequest;
import com.example.homework003.model.dto.response.VenueResponse;

import java.util.List;

public interface VenueService {

    List<VenueResponse> findAllVenue(Integer offset, Integer limit);

    VenueResponse insert(VenueRequest venueRequest);

    VenueResponse findVenueById(Integer id);
    VenueResponse updateVenueById(Integer id, VenueRequest venueRequest);

    String removeVenueById(Integer id);
}
