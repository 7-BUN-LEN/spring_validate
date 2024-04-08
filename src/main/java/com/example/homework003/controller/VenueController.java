package com.example.homework003.controller;

import com.example.homework003.model.apiResponse.APIResponse;
import com.example.homework003.model.dto.request.VenueRequest;
import com.example.homework003.model.dto.response.VenueResponse;
import com.example.homework003.service.VenueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@AllArgsConstructor
@CrossOrigin
public class VenueController {

    private final VenueService venueService;

        @GetMapping
                public ResponseEntity<APIResponse<List<VenueResponse>>>
                findAllVenue(@RequestParam (defaultValue = "1") @Positive Integer offset,
                            @RequestParam(defaultValue = "5") @Positive  Integer limit){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                            "All Venues have been successfully fetched.",
                            venueService.findAllVenue(offset,limit),
                            HttpStatus.OK,
                            LocalDateTime.now()
                    )
            );
    }

    @PostMapping
    public ResponseEntity<APIResponse<VenueResponse>> insert(@RequestBody @Valid VenueRequest venueRequest){
            APIResponse<VenueResponse> response = APIResponse.<VenueResponse>builder()
                    .message("The Venue has been Successfully Insert.")
                    .payload(venueService.insert(venueRequest))
                    .status(HttpStatus.OK)
                    .dateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<VenueResponse>>
    findVenueById(@PathVariable @Positive Integer id){
        APIResponse<VenueResponse> response = APIResponse.<VenueResponse>builder()
                .message("The Venue has been Successfully found.")
                .payload(venueService.findVenueById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<VenueResponse>>
    updateVenueById(@PathVariable Integer id, @RequestBody @Valid VenueRequest venueRequest){
            APIResponse<VenueResponse> response = APIResponse.<VenueResponse>builder()
                    .message("The Venue has been Successfully updated.")
                    .payload(venueService.updateVenueById(id,venueRequest))
                    .status(HttpStatus.OK)
                    .dateTime(LocalDateTime.now())
                    .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> removeVenueById(@PathVariable Integer id) {
        APIResponse<String> response = APIResponse.<String>builder()
                .message("The Venue has been Successfully deleted.")
                .payload(venueService.removeVenueById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
            return ResponseEntity.ok(response);
    }

}
