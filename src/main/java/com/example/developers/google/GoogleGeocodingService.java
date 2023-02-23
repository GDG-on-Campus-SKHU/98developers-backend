package com.example.developers.google;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoogleGeocodingService {

    @Transactional
    public GeocodingResult[] GeocodingTest(String location) throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey("google API 키");
        GeocodingResult[] results =  GeocodingApi.geocode(context, location).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0]));

// Invoke .shutdown() after your application is done making requests
        return results;
    }

}
