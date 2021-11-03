package ru.maksmerfy.testappforntiteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.maksmerfy.testappforntiteam.dto.request.PlanetPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.PlanetGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;
import ru.maksmerfy.testappforntiteam.models.Overlord;
import ru.maksmerfy.testappforntiteam.models.Planet;
import ru.maksmerfy.testappforntiteam.repositories.OverlordRepository;
import ru.maksmerfy.testappforntiteam.repositories.PlanetRepository;
import ru.maksmerfy.testappforntiteam.services.PlanetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {
    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private OverlordRepository overlordRepository;

    @Override
    public PlanetGetResponse findInfoByName(String name) {
        PlanetGetResponse planetGetResponse = new PlanetGetResponse("", "");
        if (validateGetRequest(name)){
            Planet planet = planetRepository.findPlanetByName(name);
            if (planet != null){
                planetGetResponse.setName(planet.getName());
                planetGetResponse.setOverlord((planet.getOverlord() == null) ?"":planet.getOverlord().getName());
            }
        }
        return planetGetResponse;
    }

    private Boolean validateGetRequest(String name) {
        if (name == null || name.equals("")) return false;
        return true;
    }

    private List<String> validateRequest(PlanetPostRequest planetPostRequest, Boolean validateOverlord){
        List<String> errorMessages = new ArrayList<String>();
        if (planetPostRequest == null) errorMessages.add("Json not valid");
        if (planetPostRequest.getName() == null || planetPostRequest.getName().equals("")) errorMessages.add("name not valid");
        if (validateOverlord && planetPostRequest.getOverlord() != null
                && !planetPostRequest.getOverlord().equals("")
                && overlordRepository.findOverlordsByName(planetPostRequest.getOverlord()) == null) errorMessages.add("Can't find overlord");
        return errorMessages;
    }

    private List<String> validatePostRequest(PlanetPostRequest planetPostRequest) {
        List<String> errorMessages = validateRequest(planetPostRequest, true);
        if (planetRepository.findPlanetByName(planetPostRequest.getName()) != null) errorMessages.add("Planet is exist in base");
        return errorMessages;
    }

    private List<String> validateSetRequest(PlanetPostRequest planetPostRequest) {
        List<String> errorMessages = validateRequest(planetPostRequest, true);
        if (planetRepository.findPlanetByName(planetPostRequest.getName()) == null) errorMessages.add("Can't find planet");
        return errorMessages;
    }

    private List<String> validatedestroyRequest(PlanetPostRequest planetPostRequest) {
        List<String> errorMessages = validateRequest(planetPostRequest, false);
        if (planetRepository.findPlanetByName(planetPostRequest.getName()) == null) errorMessages.add("Can't find planet");
        return errorMessages;
    }

    @Override
    public PostResponse savePlanet(PlanetPostRequest planetPostRequest) {
        PostResponse postResponse = new PostResponse(HttpStatus.BAD_REQUEST,new ArrayList<String>());
        postResponse.setErrorMessages(validatePostRequest(planetPostRequest));
        if (postResponse.getErrorMessages().isEmpty()){
            Planet planet = new Planet();
            planet.setName(planetPostRequest.getName());
            Overlord overlord = overlordRepository.findOverlordsByName(planetPostRequest.getOverlord());
            if (overlord != null) planet.setOverlord(overlord);
            planetRepository.save(planet);
            postResponse.setStatus(HttpStatus.OK);
        }
        return postResponse;
    }

    @Override
    public PostResponse setPlanetOverlord(PlanetPostRequest planetPostRequest) {
        PostResponse postResponse = new PostResponse(HttpStatus.BAD_REQUEST,new ArrayList<String>());
        postResponse.setErrorMessages(validateSetRequest(planetPostRequest));
        if (postResponse.getErrorMessages().isEmpty()){
            Planet planet = planetRepository.findPlanetByName(planetPostRequest.getName());
            Overlord overlord = overlordRepository.findOverlordsByName(planetPostRequest.getOverlord());
            if (overlord != null) planet.setOverlord(overlord);
            planetRepository.save(planet);
            postResponse.setStatus(HttpStatus.OK);
        }
        return postResponse;
    }

    @Override
    public PostResponse destroyPlanet(PlanetPostRequest planetPostRequest) {
        PostResponse postResponse = new PostResponse(HttpStatus.BAD_REQUEST,new ArrayList<String>());
        postResponse.setErrorMessages(validatedestroyRequest(planetPostRequest));
        if (postResponse.getErrorMessages().isEmpty()){
            Planet planet = planetRepository.findPlanetByName(planetPostRequest.getName());
            planetRepository.delete(planet);
            postResponse.setStatus(HttpStatus.OK);
        }
        return postResponse;
    }
}
