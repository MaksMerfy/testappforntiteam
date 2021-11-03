package ru.maksmerfy.testappforntiteam.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlanetPostRequest {
    private String name;
    private String overlord;
}
