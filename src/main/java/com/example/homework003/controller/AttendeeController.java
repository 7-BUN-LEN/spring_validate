package com.example.homework003.controller;

import com.example.homework003.model.apiResponse.APIResponse;
import com.example.homework003.model.dto.request.AttendeeRequest;
import com.example.homework003.model.dto.response.AttendeeResponse;
import com.example.homework003.service.AttendeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@AllArgsConstructor
@CrossOrigin
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
        public ResponseEntity<APIResponse<List<AttendeeResponse>>>
        findAllAttendee(@RequestParam (defaultValue = "1") @Positive Integer offset,
                        @RequestParam (defaultValue = "5") @Positive Integer limit){
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "All Attendee have been successfully fetched.",
                        attendeeService.findAllAttendee(offset, limit),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }
    @PostMapping
    public ResponseEntity<APIResponse<AttendeeResponse>>
    insert(@RequestBody @Valid AttendeeRequest attendeeRequest){
        APIResponse<AttendeeResponse> response = APIResponse.<AttendeeResponse>builder()
                .message("The Attendee has been Successfully Insert.")
                .payload(attendeeService.insert(attendeeRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AttendeeResponse>>
    findAttendeeById(@PathVariable @Positive Integer id){
        APIResponse<AttendeeResponse> response = APIResponse.<AttendeeResponse>builder()

                .message("The Attendee has been Successfully found.")
                .payload(attendeeService.findAttendeeById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AttendeeResponse>>
    updateAttendeeById(@PathVariable Integer id, @RequestBody @Valid AttendeeRequest attendeeRequest){
        APIResponse<AttendeeResponse> response = APIResponse.<AttendeeResponse>builder()
                .message("The Attendee has been Successfully updated.")
                .payload(attendeeService.updateAttendeeById(id, attendeeRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> removeAttendeeById(@PathVariable Integer id){
        APIResponse<String> response = APIResponse.<String>builder()
                .message("The Attendee has been Successfully deleted.")
                .payload(attendeeService.removeAttendeeById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


}
