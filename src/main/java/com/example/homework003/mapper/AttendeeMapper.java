package com.example.homework003.mapper;

import com.example.homework003.model.Attendee;
import com.example.homework003.model.dto.response.AttendeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendeeMapper {

    AttendeeMapper INSTANCE = Mappers.getMapper(AttendeeMapper.class);
    AttendeeResponse toResponse(Attendee attendee);

}
