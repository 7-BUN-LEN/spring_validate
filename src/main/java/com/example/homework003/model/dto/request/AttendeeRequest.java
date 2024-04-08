package com.example.homework003.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotNull
    @NotBlank
    private String attendeeName;


    @NotNull
    @NotBlank(message = "email is blank or empty")
    @Email
    private String email;

}
