package ru.maksmerfy.testappforntiteam.services;

import ru.maksmerfy.testappforntiteam.dto.request.PlanetPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.PlanetGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;

import java.util.List;

public interface PlanetService {
    PlanetGetResponse findInfoByName(String name);
    PostResponse savePlanet(PlanetPostRequest planetPostRequest);
    PostResponse setPlanetOverlord(PlanetPostRequest planetPostRequest);
    PostResponse destroyPlanet(PlanetPostRequest planetPostRequest);
}
