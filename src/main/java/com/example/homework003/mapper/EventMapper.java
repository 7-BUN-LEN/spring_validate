package com.example.homework003.mapper;

import com.example.homework003.model.Event;
import com.example.homework003.model.dto.response.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    EventResponse toResponse(Event event);
}
