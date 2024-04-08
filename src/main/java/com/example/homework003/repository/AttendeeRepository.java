package com.example.homework003.repository;

import com.example.homework003.model.Attendee;
import com.example.homework003.model.dto.request.AttendeeRequest;
import com.example.homework003.model.dto.response.AttendeeResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface AttendeeRepository {

    // Get All
    @Select("""
            SELECT * FROM attendees LIMIT #{limit} OFFSET #{offset};
        """)
    @Results(id = "AttendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")


    })
    List<Attendee> findAllAttendee(Integer offset, Integer limit);


    // Insert Attendees

    @Select("""
            INSERT INTO attendees (attendee_name,email) VALUES (#{attendee.attendeeName}, #{attendee.email}) RETURNING *
        """)
    @ResultMap("AttendeeMapper")
    Attendee insert(@Param("attendee")AttendeeRequest attendeeRequest);


    // Get Attendee By ID

    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{id};
           """)
    @ResultMap("AttendeeMapper")
    Attendee findAttendeeById(Integer id);

    // Update Attendee By ID

    @Select("""
        UPDATE attendees SET attendee_name = #{attendee.attendeeName}, email = #{attendee.email} WHERE attendee_id = #{id} RETURNING *
        """)
    @ResultMap("AttendeeMapper")
    Attendee updateAttendeeById(Integer id, @Param("attendee") AttendeeRequest attendeeRequest);


    // Delete Attendee By ID

    @Delete("""
        DELETE  FROM attendees WHERE attendee_id = #{id};
        """)
    boolean removeAttendeeById(Integer id);
}
