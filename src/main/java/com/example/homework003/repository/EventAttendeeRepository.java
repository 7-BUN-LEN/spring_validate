package com.example.homework003.repository;

import com.example.homework003.model.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {
    @Select("""
            SELECT * FROM  event_attendee ea INNER JOIN attendees a ON ea.attendee_id = a.attendee_id WHERE ea.event_id = #{id};
    """)
    @Results(id = "eventAttendeeMapper", value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    List<Attendee> findAllEventByAttendee(Integer id);

}
