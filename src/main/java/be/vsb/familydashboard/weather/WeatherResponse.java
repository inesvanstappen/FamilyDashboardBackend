package be.vsb.familydashboard.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherResponse(Current current, Forecast forecast) {
    public record Current(
            @JsonProperty("temp_c") double tempC,
            Condition condition
    ) {}

    public record Condition(String text, String icon, String code) {}

    public record Forecast(
            @JsonProperty("forecastday") List<ForecastDay> days
    ) {}

    public record ForecastDay(
            String date,
            Day day
    ) {}

    public record Day(
            @JsonProperty("maxtemp_c") double maxTempC,
            @JsonProperty("mintemp_c") double minTempC,
            Condition condition
    ) {}
}


