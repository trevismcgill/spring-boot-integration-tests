package com.whetherapi.integration_test.controller

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.wiremock.spring.EnableWireMock

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock
class WeatherDataControllerIT {
    companion object {
        private val wireMockServer = WireMockServer(WireMockConfiguration.options().dynamicPort())

        @JvmStatic
        @BeforeAll
        fun setup() {
            wireMockServer.start()
        }

        @JvmStatic
        @AfterAll
        fun teardown() {
            wireMockServer.stop()
        }

        @JvmStatic
        @DynamicPropertySource
        fun overrideProperties(registry: DynamicPropertyRegistry) {
            registry.add("weather.api.url") { wireMockServer.baseUrl() }
        }
    }

    @Test
    fun getTodaysWeatherData() {
        stubFor(get("/today").willReturn(ok()))
    }
}
