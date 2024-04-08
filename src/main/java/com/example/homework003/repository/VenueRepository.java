package com.example.homework003.repository;
import com.example.homework003.model.Venue;
import com.example.homework003.model.dto.request.VenueRequest;
import com.example.homework003.model.dto.response.VenueResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    // Get ALL
    @Select("""
            SELECT  * FROM venues LIMIT #{limit} OFFSET #{offset};
        """)
    @Results(id = "VenueMapper", value = {
            @Result(property = "venueId", column = "venue_id")
            ,@Result(property = "venueName" , column = "venue_name")
    })
    List<Venue> findAllVenue(Integer offset, Integer limit);

    // Insert Venue

    @Select("""
        INSERT INTO  venues (venue_name, location) VALUES (#{venue.venueName},#{venue.location}) RETURNING *
        """)
    @ResultMap("VenueMapper")
    Venue insert(@Param("venue") VenueRequest venueRequest);

    // Get Venue By ID

    @Select("""
        SELECT  * FROM venues WHERE venue_id = #{id};
        """)
    @ResultMap("VenueMapper")
    Venue findVenueById(Integer id);


    // Update Venue By ID
    @Select("""
        UPDATE venues SET venue_name = #{venue.venueName}, location = #{venue.location} WHERE venue_id = #{id} RETURNING *
        """)
    @ResultMap("VenueMapper")
    Venue updateVenueById(Integer id, @Param("venue") VenueRequest venueRequest);

    @Delete("""
        DELETE  FROM venues WHERE venue_id = #{id} ;
        """)
    boolean removeVenueById(Integer id);
}
