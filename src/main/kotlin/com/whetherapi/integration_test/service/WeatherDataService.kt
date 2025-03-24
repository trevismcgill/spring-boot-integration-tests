package com.whetherapi.integration_test.service

import com.whetherapi.integration_test.representation.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class WeatherDataService{
    @Value("\${weather.api.url}")
    lateinit var apiUrl: String

    fun getTodaysWeatherData(): WeatherResponse? {
        val weatherWebClient = WebClient.create("$apiUrl")

        val response = weatherWebClient.get()
            .retrieve()
            .bodyToMono<WeatherResponse>()
            .block()
        return response
    }
}
