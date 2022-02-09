
package com.disneyworld.demo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiErrorDto {
    
    private HttpStatus status;

    private String message;

    private List<String> errors;
    
}
