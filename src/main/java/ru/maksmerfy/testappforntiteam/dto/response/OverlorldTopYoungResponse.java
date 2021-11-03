package ru.maksmerfy.testappforntiteam.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.maksmerfy.testappforntiteam.dto.YoungOverlord;

import java.util.List;

@Data
@Getter
@Setter
public class OverlorldTopYoungResponse {
    private List<YoungOverlord> youngOverlords;
}
