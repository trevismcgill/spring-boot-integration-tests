package com.whetherapi.integration_test.controller

import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.WebClient
import org.wiremock.spring.EnableWireMock
import reactor.core.publisher.Mono
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock
class WeatherDataControllerIT {
    @LocalServerPort
    var port: Int = 0

    @Value("\${wiremock.server.baseUrl}")
    lateinit var wireMockBaseUrl: String

    @BeforeEach
    fun setupProperty() {
        System.setProperty(
            "weather.api.url",
            "$wireMockBaseUrl/data/2.5/weather?lat=35.925064&lon=-86.868889&appid=49271f2e99c47f3379cb08e2747728bd"
        )
    }

    @Test
    fun getTodaysWeatherData() {
        stubFor(
            get(urlEqualTo("/data/2.5/weather?lat=35.925064&lon=-86.868889&appid=49271f2e99c47f3379cb08e2747728bd"))
                .willReturn(ok("""
            {
              "coord": { "lon": -86.868889, "lat": 35.925064 },
              "weather": [ { "id": 800, "main": "Clear", "description": "Sunny", "icon": "01d" } ],
              "base": "stations",
              "main": {
                "temp": 72.0,
                "feels_like": 70.0,
                "temp_min": 70.0,
                "temp_max": 75.0,
                "pressure": 1012,
                "humidity": 50,
                "sea_level": 1012,
                "grnd_level": 1000
              },
              "visibility": 10000,
              "wind": { "speed": 5.14, "deg": 240, "gust": 7.0 },
              "clouds": { "all": 0 },
              "dt": 1711370000,
              "sys": { "type": 1, "id": 1234, "country": "US", "sunrise": 1711350000, "sunset": 1711393200 },
              "timezone": -18000,
              "id": 1234567,
              "name": "Brentwood",
              "cod": 200
            }
        """.trimIndent()).withHeader("Content-Type", "application/json"))
        )

        val weatherWebClient = WebClient.create("http://localhost:$port")

        val response = weatherWebClient.get().uri("/today").exchangeToMono { Mono.just(it.statusCode()) }.block()
        assertEquals(HttpStatus.OK, response)
    }
}
