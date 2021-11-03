package ru.maksmerfy.testappforntiteam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class PostResponse {
    private HttpStatus status;
    private List<String> errorMessages;
}
