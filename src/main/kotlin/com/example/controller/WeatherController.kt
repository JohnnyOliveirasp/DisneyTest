package com.example.controller

import com.example.model.WeatherResponse
import com.example.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/forecast")
    fun getTodayForecast(): Mono<WeatherResponse> {
        return weatherService.getTodayForecast()
    }
}
