package be.vsb.familydashboard.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    Weather findCurrentWeather() throws WeatherNotFound {
        return weatherService.getCurrentWeather()
                .orElseThrow(WeatherNotFound::new);
    }
}
