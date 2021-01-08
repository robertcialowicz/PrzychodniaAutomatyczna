package com.iet.przychodnia.BookVisitSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ExtractMedsAsList {
    public static List<UUID> ExtractMedsAsListFromString(String input) {
        List<UUID> uuidsList = new ArrayList<>();
        if (input != null ){
            String[] uuidStrings = input.split(",");
            for (String uuid : uuidStrings){
                uuidsList.add(UUID.fromString(uuid.trim()));
            }
        }
        return uuidsList;
    }
}
