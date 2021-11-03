package ru.maksmerfy.testappforntiteam.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.maksmerfy.testappforntiteam.dto.request.OverlordPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.LoaferResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlordGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlorldTopYoungResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;

public interface OverlordControllerApi {
    OverlordGetResponse overlordGet(@PathVariable(value = "name") String name);
    OverlorldTopYoungResponse overlordTopYoungGet();
    PostResponse overlordPost(@RequestBody OverlordPostRequest overlordPostRequest);
    LoaferResponse getLoafer();
}
