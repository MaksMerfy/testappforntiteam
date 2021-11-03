package ru.maksmerfy.testappforntiteam.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class LoaferResponse {
    private List<String> listLoafers;
}
