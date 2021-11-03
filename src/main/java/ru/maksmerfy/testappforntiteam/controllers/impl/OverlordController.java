package ru.maksmerfy.testappforntiteam.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.maksmerfy.testappforntiteam.controllers.OverlordControllerApi;
import ru.maksmerfy.testappforntiteam.dto.request.OverlordPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.LoaferResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlordGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlorldTopYoungResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;
import ru.maksmerfy.testappforntiteam.services.OverlordService;

@RestController
public class OverlordController implements OverlordControllerApi {
    @Autowired
    private OverlordService overlordService;

    @Override
    @GetMapping("/overlord/{name}")
    public OverlordGetResponse overlordGet(@PathVariable(value = "name") String name) {
        return overlordService.findInfoByName(name);
    }

    @Override
    @PostMapping("/overlord")
    public PostResponse overlordPost(@RequestBody OverlordPostRequest overlordPostRequest) {
        return overlordService.saveOverlord(overlordPostRequest);
    }

    @Override
    @GetMapping("/loafers")
    public LoaferResponse getLoafer() {
        return overlordService.getAllLoafer();
    }

    @Override
    @GetMapping("/overlord")
    public OverlorldTopYoungResponse overlordTopYoungGet() {
        return overlordService.getYoungOverlord();
    }
}
