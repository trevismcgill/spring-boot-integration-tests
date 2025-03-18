package com.whetherapi.integration_test.service

import com.whetherapi.integration_test.representation.WeatherResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class WeatherDataService{
    val weatherWebClient = WebClient.create("https://api.openweathermap.org/data/2.5/weather?lat=35.925064&lon=-86.868889&appid=49271f2e99c47f3379cb08e2747728bd")

    fun getTodaysWeatherData() {
        val response = weatherWebClient.get()
            .retrieve()
            .bodyToMono<WeatherResponse>()
            .block()

        println(response)
    }
}
