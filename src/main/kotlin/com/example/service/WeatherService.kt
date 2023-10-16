package com.example.service

import com.example.model.DailyForecast
import com.example.model.WeatherResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WeatherService {

    private val webClient = WebClient.create("https://api.weather.gov")

    fun getTodayForecast(): Mono<WeatherResponse> {
        return webClient.get()
            .uri("/gridpoints/MLB/33,70/forecast")
            .retrieve()
            .bodyToMono(WeatherAPIResponse::class.java)
            .map { apiResponse ->
                val todayForecast = apiResponse.properties.periods[0]
                WeatherResponse(
                    daily = listOf(
                        DailyForecast(
                            day_name = todayForecast.name,
                            temp_high_celsius = (todayForecast.temperature - 32) * 5.0/9.0,
                            forecast_blurp = todayForecast.shortForecast
                        )
                    )
                )
            }
    }
}

data class WeatherAPIResponse(
    val properties: WeatherProperties
)

data class WeatherProperties(
    val periods: List<WeatherPeriod>
)

data class WeatherPeriod(
    val name: String,
    val temperature: Double,
    val shortForecast: String
)
