package com.example.homework003.mapper;

import com.example.homework003.model.Venue;
import com.example.homework003.model.dto.response.VenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VenueMapper {
    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);
    VenueResponse toResponse(Venue venue);

}

