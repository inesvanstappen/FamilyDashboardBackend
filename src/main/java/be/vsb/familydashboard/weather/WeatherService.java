package be.vsb.familydashboard.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    private final String apiKey;
    private final String coordinates;
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository,
                          @Value("${API_KEY_WEATHERAPI}") String apiKey,
                          @Value("${weatherapi.coordinates}") String coordinates) {
        this.weatherRepository = weatherRepository;
        this.apiKey = apiKey;
        this.coordinates = coordinates;
    }

    public Optional<Weather> getCurrentWeather() {
        WeatherResponse weatherResponse = weatherRepository.findByLocation(apiKey, coordinates, 3);
        double now = weatherResponse.current().tempC();
        List<ForecastSummary> summaries = weatherResponse.forecast().days().stream()
                .map(fd -> new ForecastSummary(
                        fd.date(),
                        fd.day().maxTempC(),
                        fd.day().minTempC(),
                        fd.day().condition().text(),
                        fd.day().condition().code(),
                        fd.day().condition().icon()
                ))
                .collect(Collectors.toList());

        return Optional.of(new Weather(now, summaries));
    }
}
