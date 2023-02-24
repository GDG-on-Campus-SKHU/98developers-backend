package com.example.developers.google;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleGeocodingService {

    @Transactional
    public Map<String, Double> Geocoding(String address) throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey("google API 키");
        GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Address = "+ address);
        System.out.println(gson.toJson(results[0].geometry.location));
        HashMap<String, Double> location = new HashMap<String, Double>();
        location.put("lat", results[0].geometry.location.lat);
        location.put("lng", results[0].geometry.location.lng);

// Invoke .shutdown() after your application is done making requests
        return location;
    }

}
