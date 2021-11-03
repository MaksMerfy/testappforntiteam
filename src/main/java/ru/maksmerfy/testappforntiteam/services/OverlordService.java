package ru.maksmerfy.testappforntiteam.services;

import ru.maksmerfy.testappforntiteam.dto.request.OverlordPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.LoaferResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlordGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlorldTopYoungResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;

import java.util.List;

public interface OverlordService {
    OverlordGetResponse findInfoByName(String name);
    Boolean validateGetRequest(String name);
    List<String> validatePostRequest(OverlordPostRequest overlordPostRequest);
    PostResponse saveOverlord(OverlordPostRequest overlordPostRequest);
    LoaferResponse getAllLoafer();
    OverlorldTopYoungResponse getYoungOverlord();
}
