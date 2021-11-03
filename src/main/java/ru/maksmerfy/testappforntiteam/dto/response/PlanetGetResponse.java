package ru.maksmerfy.testappforntiteam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanetGetResponse {
    private String name;
    private String overlord;
}
