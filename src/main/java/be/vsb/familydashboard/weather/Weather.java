package be.vsb.familydashboard.weather;

import java.util.List;

public record Weather(
        double tempNow,
        List<ForecastSummary> forecast
) {}