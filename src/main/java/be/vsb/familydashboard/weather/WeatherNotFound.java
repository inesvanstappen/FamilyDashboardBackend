package be.vsb.familydashboard.weather;

public class WeatherNotFound extends RuntimeException {
    public WeatherNotFound() {
        super("Weather data not found");
    }
}
