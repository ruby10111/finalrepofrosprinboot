package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.API.response.weatherresponse;
import net.engineeringdigest.journalApp.cache.AppCache;

import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class weatherservice {
//    private static final String API_KEY="2d605b4d24ef991d941d92c6942f9ade";
    @Value("${weather.api.key}")
    private String APIkey;
//    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;
    @Autowired
    private RedisService redisService;



    public weatherresponse getWeather(String city){

        weatherresponse weatherresponse = redisService.get("Weather_of_" + city, weatherresponse.class);
        if(weatherresponse!=null){
            return weatherresponse;
        }
        else{
            String finalapi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY, APIkey);

            ResponseEntity<weatherresponse> responseEntity = restTemplate.exchange(finalapi, HttpMethod.GET, null, weatherresponse.class);
            weatherresponse body = responseEntity.getBody();
            if(body!=null){
                redisService.set("Weather_of_" + city,body,300l);
            }
            return body;
        }
        }





    //This will not work as weather api does not accept psot call in free version. This is just a demonstration of how a post call will be called
//    public weatherresponse Sendweather(String city){
//        String finalapi = API.replace("CITY", city).replace("API_KEY", API_KEY);
//
//        String requestBody="{\n" +
//                "    \"userName\":\"saurav\",\n" +
//                "    \"password\":\"saurav123\"\n" +
//                "}  ";
//        HttpEntity<String> httpEntity=new HttpEntity<>(requestBody);
//        ResponseEntity<weatherresponse> responseEntity = restTemplate.exchange(finalapi, HttpMethod.POST, httpEntity, weatherresponse.class);
//        weatherresponse body = responseEntity.getBody();
//        return body;
//    }



}
