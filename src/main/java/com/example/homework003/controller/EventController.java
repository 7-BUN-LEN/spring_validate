package com.example.homework003.controller;

import com.example.homework003.model.apiResponse.APIResponse;
import com.example.homework003.model.dto.request.EventRequest;
import com.example.homework003.model.dto.response.EventResponse;
import com.example.homework003.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v2/events")
@AllArgsConstructor
@CrossOrigin
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<APIResponse<List<EventResponse>>>
    findAllEvents(@RequestParam (defaultValue = "1") @Positive Integer offset,
                  @RequestParam(defaultValue = "5") @Positive Integer limit){
        System.out.println(eventService.findAllEvents(offset, limit));
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "All Events have been successfully fetch.",
                        eventService.findAllEvents(offset, limit),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }
    @PostMapping
        public ResponseEntity<APIResponse<EventResponse>>
        insert(@RequestBody @Valid EventRequest eventRequest){
            APIResponse<EventResponse> response = APIResponse.<EventResponse>builder()
                    .message("The Event has been Successfully Insert.")
                    .payload(eventService.insert(eventRequest))
                    .status(HttpStatus.OK)
                    .dateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<EventResponse>>
    findEventById(@PathVariable @Positive Integer id){
        APIResponse<EventResponse> response = APIResponse.<EventResponse>builder()
                .message("The Event has been Successfully found.")
                .payload(eventService.findEventById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<EventResponse>>
    updateEventById(@PathVariable Integer id, @RequestBody @Valid EventRequest eventRequest){
        APIResponse<EventResponse> response = APIResponse.<EventResponse>builder()
                .message("The Event has been Successfully updated.")
                .payload(eventService.updateEventById(id,eventRequest))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> removeEventById(@PathVariable Integer id) {
        APIResponse<String> response = APIResponse.<String>builder()
                .message("The Event has been Successfully deleted.")
                .payload(eventService.removeEventById(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
