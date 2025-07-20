package be.vsb.familydashboard.weather;

public record ForecastSummary(
        String date,
        double maxTemp,
        double minTemp,
        String condition,
        String icon,
        String code
) {}