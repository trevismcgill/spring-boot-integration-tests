package com.whetherapi.integration_test.controller

import com.whetherapi.integration_test.representation.WeatherResponse
import com.whetherapi.integration_test.service.WeatherDataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherDataController(
    val weatherDataService: WeatherDataService
){
    @GetMapping("/today")
    fun collectTodayData(): WeatherResponse? {
        return weatherDataService.getTodaysWeatherData()
    }
}
