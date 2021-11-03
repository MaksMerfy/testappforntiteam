package ru.maksmerfy.testappforntiteam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverlordPostRequest {
    private String name = "";
    private String age;
}
