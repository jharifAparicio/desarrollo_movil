package com.example.reactivo_clima

data class WeatherResponse(
    val current_weather: CurrentWeather,
    val current_weather_units: CurrentWeatherUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)