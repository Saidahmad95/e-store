package com.example.estore.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiFailedResponse {
    private HttpStatus httpStatus;
    private String message;
}
