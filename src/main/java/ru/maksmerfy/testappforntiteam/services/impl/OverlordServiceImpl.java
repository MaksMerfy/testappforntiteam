package ru.maksmerfy.testappforntiteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.maksmerfy.testappforntiteam.dto.request.OverlordPostRequest;
import ru.maksmerfy.testappforntiteam.dto.response.LoaferResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlordGetResponse;
import ru.maksmerfy.testappforntiteam.dto.response.OverlorldTopYoungResponse;
import ru.maksmerfy.testappforntiteam.dto.response.PostResponse;
import ru.maksmerfy.testappforntiteam.models.Overlord;
import ru.maksmerfy.testappforntiteam.models.Planet;
import ru.maksmerfy.testappforntiteam.repositories.OverlordRepository;
import ru.maksmerfy.testappforntiteam.repositories.PlanetRepository;
import ru.maksmerfy.testappforntiteam.services.OverlordService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OverlordServiceImpl implements OverlordService {
    @Autowired
    private OverlordRepository overlordRepository;
    @Autowired
    private PlanetRepository planetRepository;

    @Override
    public OverlordGetResponse findInfoByName(String name) {
        OverlordGetResponse overlordGetResponse = new OverlordGetResponse("", 0, new ArrayList<String>());
        if (validateGetRequest(name)) {
            Overlord overlord = overlordRepository.findOverlordsByName(name);
            if (overlord == null) return overlordGetResponse;
            List<String> overlordList = planetRepository.findPlanetsNameByOverlord(overlord.getId());
            if (overlordList == null) return overlordGetResponse;
            overlordGetResponse.setName(overlord.getName());
            overlordGetResponse.setAge(overlord.getAge());
            overlordGetResponse.setPlanets(overlordList);
        }
        return overlordGetResponse;
    }

    @Override
    public Boolean validateGetRequest(String name) {
        if (name == null || name.equals("")) return false;
        return true;
    }

    @Override
    public PostResponse saveOverlord(OverlordPostRequest overlordPostRequest) {
        PostResponse postResponse = new PostResponse(HttpStatus.BAD_REQUEST,new ArrayList<String>());
        postResponse.setErrorMessages(validatePostRequest(overlordPostRequest));
        if (postResponse.getErrorMessages().isEmpty()){
            Overlord overlord = new Overlord();
            overlord.setName(overlordPostRequest.getName());
            overlord.setAge(Integer.parseInt(overlordPostRequest.getAge()));
            overlordRepository.save(overlord);
            postResponse.setStatus(HttpStatus.OK);
        }
        return postResponse;
    }

    @Override
    public List<String> validatePostRequest(OverlordPostRequest overlordPostRequest) {
        List<String> errorMessages = new ArrayList<String>();
        if (overlordPostRequest == null) errorMessages.add("Json not valid");
        if (overlordPostRequest.getName() == null || overlordPostRequest.getName().equals("")) errorMessages.add("name not valid");
        if (overlordRepository.findOverlordsByName(overlordPostRequest.getName()) != null) errorMessages.add("Overlord is exist in base");
        try {
            int age = Integer.parseInt(overlordPostRequest.getAge());
            if (age <= 0) errorMessages.add("age must be above 0");
        } catch (NumberFormatException e){
            errorMessages.add("age must be above 0");
        }
        return errorMessages;
    }

    @Override
    public LoaferResponse getAllLoafer() {
        LoaferResponse loaferResponse = new LoaferResponse();
        loaferResponse.setListLoafers(overlordRepository.findAllLoafers());
        return loaferResponse;
    }

    @Override
    public OverlorldTopYoungResponse getYoungOverlord() {
        OverlorldTopYoungResponse overlorldTopYoungResponse = new OverlorldTopYoungResponse();
        overlorldTopYoungResponse.setYoungOverlords(overlordRepository.findYoungOverlord());
        return overlorldTopYoungResponse;
    }
}
