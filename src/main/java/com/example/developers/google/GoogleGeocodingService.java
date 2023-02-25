package com.example.developers.google;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GoogleGeocodingService {

    @Value("${spring.google.api}")
    private String api;

//    private GeoApiContext context ;


    public Map<String, Double> geocoding(
            String address
    ) throws Exception {
//        System.out.println("@@@@@@@@@@@@@@@@@@@ "+ api);
        GeoApiContext context = new GeoApiContext().setApiKey(api);
        GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Address = "+ address);
        System.out.println(gson.toJson(results[0].geometry.location));
        HashMap<String, Double> location = new HashMap<String, Double>();
        location.put("lat", results[0].geometry.location.lat);
        location.put("lng", results[0].geometry.location.lng);

        return location;
    }

}
