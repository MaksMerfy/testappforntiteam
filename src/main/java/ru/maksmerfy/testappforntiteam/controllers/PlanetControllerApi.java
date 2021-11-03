package ru.maksmerfy.testappforntiteam.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.maksmerfy.testappforntiteam.dto.request.PlanetPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.PlanetGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;

public interface PlanetControllerApi {
    PlanetGetResponse planetGet(@PathVariable(value = "name") String name);
    PostResponse planetPost(@RequestBody PlanetPostRequest PlanetPostRequest);
    PostResponse setPlanetOverlord(@RequestBody PlanetPostRequest PlanetPostRequest);
    PostResponse destroyPlanet(@RequestBody PlanetPostRequest PlanetPostRequest);
}
