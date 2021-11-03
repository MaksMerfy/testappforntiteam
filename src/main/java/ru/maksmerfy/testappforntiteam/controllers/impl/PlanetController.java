package ru.maksmerfy.testappforntiteam.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.maksmerfy.testappforntiteam.controllers.PlanetControllerApi;
import ru.maksmerfy.testappforntiteam.dto.request.PlanetPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.PlanetGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;
import ru.maksmerfy.testappforntiteam.services.PlanetService;

@RestController
public class PlanetController implements PlanetControllerApi {
    @Autowired
    private PlanetService planetService;

    @Override
    @GetMapping("/planet/{name}")
    public PlanetGetResponse planetGet(@PathVariable(value = "name") String name) {
        return planetService.findInfoByName(name);
    }

    @Override
    @PostMapping("/planet")
    public PostResponse planetPost(@RequestBody PlanetPostRequest planetPostRequest) {
        return planetService.savePlanet(planetPostRequest);
    }

    @Override
    @PostMapping("/planet/setoverlord")
    public PostResponse setPlanetOverlord(@RequestBody PlanetPostRequest planetPostRequest) {
        return planetService.setPlanetOverlord(planetPostRequest);
    }

    @Override
    @PostMapping("/planet/destroy")
    public PostResponse destroyPlanet(@RequestBody PlanetPostRequest PlanetPostRequest) {
        return planetService.destroyPlanet(PlanetPostRequest);
    }
}
