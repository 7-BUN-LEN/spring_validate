package com.example.homework003.repository;

import com.example.homework003.model.Event;
import com.example.homework003.model.dto.request.EventRequest;
import com.example.homework003.model.dto.response.EventResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.*;


import java.util.Iterator;
import java.util.List;

@Mapper
public interface EventRepository {
    // Get All Events
    @Select("""
       
            SELECT * FROM events LIMIT #{limit} OFFSET #{offset};
        """)
    @Results(id="EventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "venueId", column = "venue_id", one =
            @One(select = "com.example.homework003.repository.VenueRepository.findVenueById")),
            @Result(property = "attendeeId", column = "event_id",
                    many = @Many(select = "com.example.homework003.repository.EventAttendeeRepository.findAllEventByAttendee"))

    })
    List<Event> findAllEvents(Integer offset, Integer limit);

//    Insert Events

    @Select("""
        INSERT INTO events (event_name, event_date, venue_id) VALUES (#{event.eventName}, #{event.event_date}, #{event.venue}) RETURNING *
        """)
    @ResultMap("EventMapper")
    Event insert(@Param("event") EventRequest eventRequest);

    @Insert("""
        INSERT INTO event_attendee(event_id, attendee_id) VALUES (#{eventId}, #{attendeeId}) RETURNING id;
        """)

    void insertToEventAttendee(Integer eventId, Integer attendeeId);

        // Get Event By ID

    @Select("""
          SELECT  * FROM events WHERE event_id = #{id};
          """)
    @ResultMap("EventMapper")
    Event findEventById( Integer id);

    // Update Event by ID

    @Select("""
            UPDATE events SET event_name = #{event.eventName}, 
            event_date = #{event.event_date} , 
            venue_id = #{event.venue} WHERE event_id = #{id} RETURNING *
            """)
    @ResultMap("EventMapper")
    Event updateEventById(Integer id, @Param("event") EventRequest eventRequest);


    @Select("""
            DELETE FROM event_attendee where event_id = #{studentId};
        """)
    void deleteAttendeeById(int studentId);

    @Select("""
        INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId}, #{attendeeId});
        """)
    void insertAttendeeByUser(int eventId, int attendeeId);

//    Delete
    @Delete("""
        DELETE FROM events WHERE event_id = #{id};
        """)
    boolean removeEventById(Integer id);


    // EventAttendeeDB




}