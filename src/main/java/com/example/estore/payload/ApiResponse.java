package com.example.estore.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private HttpStatus httpStatus;
    private Object message;
    private Object data;
}
