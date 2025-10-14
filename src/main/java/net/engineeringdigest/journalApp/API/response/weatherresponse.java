package net.engineeringdigest.journalApp.API.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class weatherresponse{
    private Current current;
    @Getter
    @Setter
    public class Current{
        private int temperature;
        private int feelslike;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
    }

}



