package ru.maksmerfy.testappforntiteam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OverlordGetResponse {
    private String name;
    private int age;
    private List<String> planets;
}
