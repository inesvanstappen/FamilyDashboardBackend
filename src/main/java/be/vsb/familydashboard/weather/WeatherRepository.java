package be.vsb.familydashboard.weather;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@Repository
public interface WeatherRepository {
    @GetExchange("http://api.weatherapi.com/v1/forecast.json")
    WeatherResponse findByLocation(@RequestParam("key") String key,
                                   @RequestParam("q") String coordinates,
                                   @RequestParam("days") int days);

}
