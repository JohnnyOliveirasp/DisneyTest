package com.example.model

data class DailyForecast(
    val day_name: String,
    val temp_high_celsius: Double,
    val forecast_blurp: String
)

data class WeatherResponse(
    val daily: List<DailyForecast>
)
